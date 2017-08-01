package com.ktt.archsample.repository

import com.ktt.archsample.dao.Record
import com.ktt.archsample.dao.RecordDao
import java.util.concurrent.Executor
import javax.inject.Inject

/**
 * @author luke_kao
 */
class RecordRepository @Inject constructor(val dao: RecordDao, val executor: Executor) {
    interface Callback {
        fun onRecordsLoaded(records: List<Record>)
    }

    fun getRecords(callback: Callback) {
        executor.execute {
            callback.onRecordsLoaded(dao.load())
        }
    }

    fun saveRecord(record: Record) {
        executor.execute {
            dao.save(record)
        }
    }
}