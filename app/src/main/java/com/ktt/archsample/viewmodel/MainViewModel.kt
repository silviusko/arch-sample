package com.ktt.archsample.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.util.Log
import com.ktt.archsample.db.entity.Record
import com.ktt.archsample.repository.RecordRepository
import com.ktt.archsample.task.DiceGenerator
import javax.inject.Inject

/**
 * @author luke_kao
 */

class MainViewModel(application: Application) : AndroidViewModel(application),
        LifecycleObserver,
        DiceGenerator.Callback {

    private val mProgressLiveData: MutableLiveData<Int> = MutableLiveData()
    private val mResultLiveData: MutableLiveData<Int> = MutableLiveData()
    private var mHistoryLiveData: MediatorLiveData<List<Record>> = MediatorLiveData()

    @Inject
    lateinit var repository: RecordRepository

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        repository.loadRecords(getApplication())

        mHistoryLiveData.addSource(repository.records, { records ->
            mHistoryLiveData.value = records
        })
    }

    fun dice() {
        mProgressLiveData.value = 0
        mResultLiveData.value = 0

        repository.dice(this)
    }

    override fun updateProgress(progress: Int?, fakeResult: Int?) {
        mProgressLiveData.value = progress!!
        mResultLiveData.value = fakeResult!!
    }

    override fun updateResult(result: Int?) {
        mResultLiveData.value = result!!

        val record = Record()
        record.value = result

        repository.saveRecord(record)
    }

    override fun onCleared() {
        Log.d("MainViewModel", "onCleared")
    }

    val progressLiveData: LiveData<Int>
        get() = mProgressLiveData

    val resultLiveData: LiveData<Int>
        get() = mResultLiveData

    val historyLiveData: LiveData<List<Record>>
        get() = mHistoryLiveData
}
