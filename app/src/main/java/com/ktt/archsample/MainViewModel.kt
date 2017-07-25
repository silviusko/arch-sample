package com.ktt.archsample

import android.app.Application
import android.arch.lifecycle.*
import android.util.Log

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

    private val repository: RecordRepository = RecordRepository(App.dataBase?.recordDao()!!, App.executor!!)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun refresh() {
        repository.getRecords(this)
    }

    fun dice() {
        mProgressLiveData.value = 0
        mResultLiveData.value = 0

        val generator = DiceGenerator(this)
        generator.execute()
    }

    override fun updateProgress(progress: Int?) {
        mProgressLiveData.value = progress
    }

    override fun updateResult(result: Int?) {
        mResultLiveData.value = result

        val record = Record()
        record.value = result!!

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
