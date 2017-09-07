package com.ktt.archsample.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import com.ktt.archsample.db.entity.Record
import com.ktt.archsample.task.DiceGenerator
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author luke_kao
 */
open class RecordRepository
@Inject constructor(context: Context,
                    private val dbCreator: DatabaseCreator,
                    private val executor: Executor) {
    private val ABSENT = MutableLiveData<List<Record>>()

    lateinit var records: LiveData<List<Record>>

    fun loadRecords(context: Context) {
        val isDbCreated = dbCreator.isCreated()
        records = Transformations.switchMap(isDbCreated, {
            if (it) {
                dbCreator.database.recordDao().loadAsync()
            } else {
                ABSENT
            }
        })

        dbCreator.create(context)
    }

    fun dice(callback: DiceGenerator.Callback) {
        val task = DiceGenerator(callback)
        task.execute()
    }


    fun saveRecord(record: Record) {
        executor.execute {
            val database = dbCreator.database
            val recordDao = database.recordDao()
            recordDao.save(record)
        }
    }
}