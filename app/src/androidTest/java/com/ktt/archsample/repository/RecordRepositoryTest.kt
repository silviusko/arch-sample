package com.ktt.archsample.repository

import android.support.test.runner.AndroidJUnit4
import com.ktt.archsample.TestUtil
import com.ktt.archsample.task.DiceGenerator
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import java.util.concurrent.CountDownLatch

/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class RecordRepositoryTest {
    private lateinit var repository: RecordRepository

    @Before
    fun setUp() {
        val component = DaggerRepositoryComponent.builder()
                .repositoryModule(RepositoryTestModule())
                .build()
        repository = RecordRepository(component.provideRecordDao(), component.provideExecutor())
    }

    @Test
    fun dice() {
        val callback = mock(DiceGenerator.Callback::class.java)
        val countdown = CountDownLatch(1)

        `when`(callback.updateResult(anyInt()))
                .then { countdown.countDown() }

        repository.dice(callback)

        countdown.await()

        verify(callback).updateResult(anyInt())
    }

    @Test
    fun getRecords() {
        repository.getRecords()
        verify(repository.dao).loadAsync()
    }

    @Test
    fun saveRecord() {
        val record = TestUtil.newRecord()

        repository.saveRecord(record)

        verify(repository.dao).save(record)
    }
}
