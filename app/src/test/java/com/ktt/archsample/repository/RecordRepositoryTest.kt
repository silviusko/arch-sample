package com.ktt.archsample.repository

import com.ktt.archsample.test.TestUtil
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

/**
 * @author luke_kao
 */
class RecordRepositoryTest {
    @Inject
    lateinit var repository: RecordRepository

    @Before
    fun setup() {
        val component = DaggerRepositoryComponent.create()
        repository = component.provideRepository()
    }

    @Test
    fun getRecords() {
        val callback = Mockito.mock(RecordRepository.Callback::class.java)

        repository.getRecords(callback)

        Mockito.verify(callback).onRecordsLoaded(Mockito.anyList())
    }

    @Test
    fun saveRecord() {
        val record = TestUtil.newRecord()

        repository.saveRecord(record)

        Mockito.verify(repository.dao).save(record)
    }
}
