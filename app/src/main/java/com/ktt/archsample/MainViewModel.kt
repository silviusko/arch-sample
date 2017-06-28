package com.ktt.archsample

import android.arch.lifecycle.*
import android.util.Log

/**
 * @author luke_kao
 */

class MainViewModel : ViewModel(), LifecycleObserver, DiceGenerator.Callback {
    private val mProgressLiveData: MutableLiveData<Int> = MutableLiveData()
    private val mResultLiveData: MutableLiveData<Int> = MutableLiveData()

    fun dice() {
        mProgressLiveData.value = 0
        mResultLiveData.value = 0

        val generator = DiceGenerator(this)
        generator.execute()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    internal fun onCreate() {
        Log.d("MainViewModel", "onCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun onResume() {
        Log.d("MainViewModel", "onResume")
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

    override fun updateProgress(progress: Int?) {
        mProgressLiveData.value = progress
    }

    override fun updateResult(result: Int?) {
        mResultLiveData.value = result
    }

    val progressLiveData: LiveData<Int>
        get() = mProgressLiveData

    val resultLiveData: LiveData<Int>
        get() = mResultLiveData
}
