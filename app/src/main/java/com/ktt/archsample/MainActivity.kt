package com.ktt.archsample

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner, View.OnClickListener {
    private val mLifecycleRegistry = LifecycleRegistry(this)

    private var mTextView: TextView? = null
    private var mButton: Button? = null
    private var mProgressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mViewModel = viewModel
        lifecycle.addObserver(mViewModel)

        mTextView = findViewById(R.id.textView) as TextView
        mButton = findViewById(R.id.button) as Button
        mProgressBar = findViewById(R.id.progressBar) as ProgressBar

        mViewModel.progressLiveData.observe(this, Observer<Int> { integer -> mProgressBar?.progress = integer!! })

        mViewModel.resultLiveData.observe(this, Observer<Int> { integer -> mTextView?.text = integer.toString() })

        mProgressBar?.max = 100
        mButton?.setOnClickListener(this)
    }

    override fun getLifecycle(): LifecycleRegistry {
        return mLifecycleRegistry
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> viewModel.dice()
        }
    }

    private val viewModel: MainViewModel
        get() = ViewModelProviders.of(this).get(MainViewModel::class.java)
}
