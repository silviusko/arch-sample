package com.ktt.archsample.repository

import com.ktt.archsample.dao.Record
import com.ktt.archsample.dao.RecordDao
import com.ktt.archsample.test.TestUtil
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
class RecordRepositoryTest {
    private lateinit var repository: RecordRepository

    @Before
    fun setup() {
        repository = RecordRepository(MockDao(), MockExecutor())
    }

    @Test
    fun getRecords() {
        val callback = MockCallback()
        repository.getRecords(callback)

        Assert.assertNotNull(callback.records)
        Assert.assertTrue(callback.records?.size!! > 0)

        val executor = repository.executor as MockExecutor
        Assert.assertEquals(1, executor.executeCount)
    }

    @Test
    fun saveRecord() {
        val record = TestUtil.newRecord()
        repository.saveRecord(record)

        val dao = repository.dao as MockDao
        Assert.assertEquals(1, dao.saveCount)

        val executor = repository.executor as MockExecutor
        Assert.assertEquals(1, executor.executeCount)
    }
}

private class MockCallback : RecordRepository.Callback {
    var records: List<Record>? = null

    override fun onRecordsLoaded(records: List<Record>) {
        this.records = records
    }
}

private class MockDao : RecordDao {
    var saveCount = 0

    override fun load(): List<Record> {
        return TestUtil.newRecords(5)
    }


    override fun loadById(id: Int): Record? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(record: Record) {
        saveCount++
    }

    override fun save(records: List<Record>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(record: Record) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(records: List<Record>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(record: Record) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

private class MockExecutor : Executor {
    var executeCount = 0
    override fun execute(runnable: Runnable?) {
        executeCount++
        runnable?.run()
    }
}
