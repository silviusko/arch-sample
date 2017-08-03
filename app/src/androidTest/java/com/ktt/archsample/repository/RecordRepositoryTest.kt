package com.ktt.archsample.repository

import android.support.test.runner.AndroidJUnit4
import com.ktt.archsample.TestUtil
import com.ktt.archsample.task.DiceGenerator
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch
import javax.inject.Inject

/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class RecordRepositoryTest {
    @Inject
    lateinit var repository: RecordRepository

    @Before
    fun setUp() {
        val component = DaggerRepositoryComponent.builder()
                .repositoryModule(RepositoryTestModule())
                .build()
        repository = RecordRepository(component.provideRecordDao(), component.provideExecutor())
    }

    @Test
    fun dice() {
        val callback = Mockito.mock(DiceGenerator.Callback::class.java)
        val countdown = CountDownLatch(1)

        Mockito.`when`(callback.updateResult(Mockito.anyInt()))
                .then { countdown.countDown() }

        repository.dice(callback)

        countdown.await()

        Mockito.verify(callback).updateResult(Mockito.anyInt())
    }

    @Test
    fun getRecords() {
        val records = TestUtil.newRecords(10)
        Mockito.`when`(repository.dao.load()).thenReturn(records)

        val callback = Mockito.mock(RecordRepository.Callback::class.java)

        repository.getRecords(callback)

        Mockito.verify(repository.dao).load()
        Mockito.verify(callback).onRecordsLoaded(records)
    }

    @Test
    fun saveRecord() {
        val record = TestUtil.newRecord()

        repository.saveRecord(record)

        Mockito.verify(repository.dao).save(record)
    }
}
