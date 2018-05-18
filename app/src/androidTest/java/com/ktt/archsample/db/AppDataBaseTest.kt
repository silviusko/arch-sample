package com.ktt.archsample.db

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.ktt.archsample.TestUtil
import com.ktt.archsample.db.dao.RecordDao
import com.ktt.archsample.db.entity.Record
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
    private val INIT_DB_SIZE = 5

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
    fun saveOneAndLoadAll() {
        val record = TestUtil.newRecord()

        recordDao.save(record)
        val records = recordDao.load()
        Assert.assertTrue(records.isNotEmpty())
        Assert.assertEquals(record.value, records[0].value)
    }

    @Test
    fun saveAllAndLoadAll() {
        val records = buildDataAndSave()

        val recordsInDb = recordDao.load()
        Assert.assertTrue(recordsInDb.isNotEmpty())
        for (i in 0 until records.size) {
            Assert.assertEquals(i + 1, recordsInDb[i].id)
            Assert.assertEquals(records[i].value, recordsInDb[i].value)
        }
    }

    @Test
    fun saveAllAndLoadOne() {
        val records = buildDataAndSave()

        val index = INIT_DB_SIZE / 2
        val recordInDb = recordDao.loadById(index + 1)
        Assert.assertNotNull(recordInDb)
        Assert.assertEquals(records[index].value, recordInDb?.value)
    }

    @Test
    fun saveAllAndLoadNoExist() {
        val records = buildDataAndSave()

        val index = records.size * 2
        val recordInDb = recordDao.loadById(index - 1)
        Assert.assertNull(recordInDb)
    }

    @Test
    fun updateOne() {
        val records = buildDataAndLoadFromDb()
        val targetRecord = records[INIT_DB_SIZE / 2]
        targetRecord.value += targetRecord.value

        recordDao.update(targetRecord)
        val recordsInDb = recordDao.load()

        Assert.assertEquals(records.size, recordsInDb.size)
        records.forEachIndexed { index, record ->
            Assert.assertEquals(record.value, recordsInDb[index].value)
        }
    }

    @Test
    fun updateAll() {
        val records = buildDataAndLoadFromDb()
        records.forEach { it.value *= it.value }

        recordDao.update(records)
        val recordsInDb = recordDao.load()

        Assert.assertEquals(records.size, recordsInDb.size)
        records.forEachIndexed { index, record ->
            Assert.assertEquals(record.value, recordsInDb[index].value)
        }
    }

    @Test
    fun deleteOne() {
        val records = buildDataAndLoadFromDb()

        val record = records[records.size / 2]
        recordDao.delete(record)

        val recordsInDb = recordDao.load()

        Assert.assertEquals(records.size - 1, recordsInDb.size)
        recordsInDb.forEach {
            Assert.assertNotEquals(record.id, it.id)
        }
    }

    private fun buildDataAndSave(): List<Record> {
        val records = TestUtil.newRecords(INIT_DB_SIZE)
        recordDao.save(records)

        return records
    }


    private fun buildDataAndLoadFromDb(): List<Record> {
        buildDataAndSave()

        return recordDao.load()
    }
}