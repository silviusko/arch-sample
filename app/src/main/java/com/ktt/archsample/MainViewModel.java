package com.ktt.archsample;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

/**
 * @author luke_kao
 */

public class MainViewModel extends ViewModel implements LifecycleObserver, DiceGenerator.Callback {
    private MutableLiveData<Integer> mProgressLiveData;
    private MutableLiveData<Integer> mResultLiveData;

    public MainViewModel() {
        mProgressLiveData = new MutableLiveData<>();
        mResultLiveData = new MutableLiveData<>();
    }

    public void dice() {
        mProgressLiveData.setValue(0);
        mResultLiveData.setValue(0);

        DiceGenerator generator = new DiceGenerator(this);
        generator.execute();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        Log.d("MainViewModel", "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        Log.d("MainViewModel", "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny() {
        Log.d("MainViewModel", "onAny");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.d("MainViewModel", "onDestroy");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        Log.d("MainViewModel", "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        Log.d("MainViewModel", "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        Log.d("MainViewModel", "onStop");
    }

    @Override
    protected void onCleared() {
        Log.d("MainViewModel", "onCleared");
    }

    @Override
    public void updateProgress(int progress) {
        mProgressLiveData.setValue(progress);
    }

    @Override
    public void updateResult(int result) {
        mResultLiveData.setValue(result);
    }

    public LiveData<Integer> getProgressLiveData() {
        return mProgressLiveData;
    }

    public LiveData<Integer> getResultLiveData() {
        return mResultLiveData;
    }
}
