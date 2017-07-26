package com.ktt.archsample.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.ktt.archsample.TestUtil
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class AppDataBaseTest {
    private lateinit var db: AppDataBase
    private lateinit var recordDao: RecordDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        recordDao = db.recordDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeRecordAndRead() {
        val record = TestUtil.newRecord()

        recordDao.save(record)
        val records = recordDao.load()
        Assert.assertTrue(records.isNotEmpty())
        Assert.assertEquals(record.value, records[0].value)
    }

    @Test
    fun writeRecordsAndRead() {
        val records = TestUtil.newRecords(5)

        recordDao.save(records)

        val recordsInDb = recordDao.load()
        Assert.assertTrue(recordsInDb.isNotEmpty())
        for (i in 0 until records.size) {
            Assert.assertEquals(records[i].value, recordsInDb[i].value)
        }
    }
}