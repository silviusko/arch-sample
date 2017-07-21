package com.ktt.archsample

import android.app.Application
import android.arch.lifecycle.*
import android.util.Log

/**
 * @author luke_kao
 */

class MainViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver, DiceGenerator.Callback {
    private val mProgressLiveData: MutableLiveData<Int> = MutableLiveData()
    private val mResultLiveData: MutableLiveData<Int> = MutableLiveData()
    private var mHistoryLiveData: LiveData<List<Record>> = MutableLiveData()

    private val repository: RecordRepository = RecordRepository(App.dataBase?.recordDao()!!, App.executor!!)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun refresh() {
        mHistoryLiveData = repository.getRecords()
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

    val progressLiveData: LiveData<Int>
        get() = mProgressLiveData

    val resultLiveData: LiveData<Int>
        get() = mResultLiveData

    val historyLiveData: LiveData<List<Record>>
        get() = mHistoryLiveData


    ///////////////////////////////////////////////
    //
    //  lifecycle events
    //
    ///////////////////////////////////////////////

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    internal fun onCreate() {
        Log.d("MainViewModel", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun onResume1() {
        Log.d("MainViewModel", "onResume1")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun onResume2() {
        Log.d("MainViewModel", "onResume2")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    internal fun onAny() {
        Log.d("MainViewModel", "onAny")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onDestroy() {
        Log.d("MainViewModel", "onDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun onPause() {
        Log.d("MainViewModel", "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    internal fun onStart() {
        Log.d("MainViewModel", "onStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun onStop() {
        Log.d("MainViewModel", "onStop")
    }

    override fun onCleared() {
        Log.d("MainViewModel", "onCleared")
    }
}
