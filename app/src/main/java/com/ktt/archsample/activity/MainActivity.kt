package com.ktt.archsample.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.ktt.archsample.R
import com.ktt.archsample.adapter.HistoryAdapter
import com.ktt.archsample.dao.Record
import com.ktt.archsample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner, View.OnClickListener {
    private val mLifecycleRegistry = LifecycleRegistry(this)

    private lateinit var mTextView: TextView
    private lateinit var mButton: Button
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mHistoryAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(viewModel)

        findViews()
        bindViews()
    }

    private fun findViews() {
        mTextView = findViewById(R.id.textView)
        mButton = findViewById(R.id.button)
        mProgressBar = findViewById(R.id.progressBar)
        mRecyclerView = findViewById(R.id.recyclerView)

        mProgressBar.max = 100
        mButton.setOnClickListener(this)

        mHistoryAdapter = HistoryAdapter()
        mRecyclerView.adapter = mHistoryAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun bindViews() {
        viewModel.progressLiveData.observe(this, Observer<Int> {
            integer ->
            mProgressBar.progress = integer!!
        })

        viewModel.resultLiveData.observe(this, Observer<Int> {
            integer ->
            mTextView.text = integer.toString()
        })

        viewModel.historyLiveData.observe(this, Observer<List<Record>> {
            list ->
            mHistoryAdapter.update(list)
            mHistoryAdapter.notifyDataSetChanged()
            mRecyclerView.smoothScrollToPosition(mHistoryAdapter.itemCount - 1)
        })
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
