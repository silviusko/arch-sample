package com.ktt.archsample;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner, View.OnClickListener {
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    private TextView mTextView;
    private Button mButton;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel mViewModel = getViewModel();
        getLifecycle().addObserver(mViewModel);

        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mViewModel.getProgressLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mProgressBar.setProgress(integer);
            }
        });

        mViewModel.getResultLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                mTextView.setText(String.valueOf(integer));
            }
        });

        mProgressBar.setMax(100);
        mButton.setOnClickListener(this);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                getViewModel().dice();
                break;
        }
    }

    private MainViewModel getViewModel() {
        return ViewModelProviders.of(this).get(MainViewModel.class);
    }
}
