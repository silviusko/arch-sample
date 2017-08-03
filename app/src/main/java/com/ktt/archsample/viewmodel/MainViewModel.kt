package com.ktt.archsample.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.util.Log
import com.ktt.archsample.dao.Record
import com.ktt.archsample.repository.RecordRepository
import com.ktt.archsample.task.DiceGenerator
import javax.inject.Inject

/**
 * @author luke_kao
 */

class MainViewModel(application: Application) : AndroidViewModel(application),
        LifecycleObserver,
        DiceGenerator.Callback,
        RecordRepository.Callback {

    private val mProgressLiveData: MutableLiveData<Int> = MutableLiveData()
    private val mResultLiveData: MutableLiveData<Int> = MutableLiveData()
    private var mHistoryLiveData: MutableLiveData<List<Record>> = MutableLiveData()

    @Inject
    lateinit var repository: RecordRepository

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun refresh() {
        repository.getRecords(this)
    }

    fun dice() {
        mProgressLiveData.value = 0
        mResultLiveData.value = 0

        repository.dice(this)
    }

    override fun updateProgress(progress: Int?) {
        mProgressLiveData.value = progress!!
    }

    override fun updateResult(result: Int?) {
        mResultLiveData.value = result!!

        val record = Record()
        record.value = result

        repository.saveRecord(record)

        refresh()
    }

    override fun onRecordsLoaded(records: List<Record>) {
        mHistoryLiveData.postValue(records)
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
