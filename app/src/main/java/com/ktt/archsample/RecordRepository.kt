package com.ktt.archsample

import android.arch.lifecycle.LiveData
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
class RecordRepository(val dao: RecordDao, val executor: Executor) {


    fun getRecords(): LiveData<List<Record>> {
        return dao.load()
    }

    fun saveRecord(record: Record) {
        executor.execute {
            dao.save(record)
        }
    }
}