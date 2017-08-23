package com.ktt.archsample.repository

import android.arch.lifecycle.LiveData
import com.ktt.archsample.dao.Record
import com.ktt.archsample.dao.RecordDao
import com.ktt.archsample.task.DiceGenerator
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author luke_kao
 */
open class RecordRepository @Inject constructor(val dao: RecordDao, val executor: Executor) {

    fun dice(callback: DiceGenerator.Callback) {
        val task = DiceGenerator(callback)
        task.execute()
    }

    fun getRecords(): LiveData<List<Record>> {
        return dao.loadAsync()
    }

    fun saveRecord(record: Record) {
        executor.execute {
            dao.save(record)
        }
    }
}