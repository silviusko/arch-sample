package com.ktt.archsample.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleRegistryOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ktt.archsample.App
import com.ktt.archsample.R
import com.ktt.archsample.adapter.HistoryAdapter
import com.ktt.archsample.db.entity.Record
import com.ktt.archsample.repository.DaggerRepositoryComponent
import com.ktt.archsample.repository.RepositoryModule
import com.ktt.archsample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner, View.OnClickListener {
    private val mLifecycleRegistry = LifecycleRegistry(this)

    private lateinit var mTextView: TextView
    private lateinit var mButton: Button
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var mHistoryAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerRepositoryComponent.builder()
                .appComponent(App.appComponent())
                .repositoryModule(RepositoryModule(applicationContext))
                .build()
                .inject(viewModel)

        lifecycle.addObserver(viewModel)

        findViews()
        bindViews()
    }

    private fun findViews() {
        mTextView = findViewById(R.id.textView)
        mButton = findViewById(R.id.button)
        mProgressBar = findViewById(R.id.progressBar)
        mRecyclerView = findViewById(R.id.recyclerView)

        mProgressBar.max = 10
        mButton.setOnClickListener(this)

        mHistoryAdapter = HistoryAdapter()
        mRecyclerView.adapter = mHistoryAdapter
        mRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
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
            if (mHistoryAdapter.itemCount > 0) {
                mRecyclerView.smoothScrollToPosition(mHistoryAdapter.itemCount - 1)
            }
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
