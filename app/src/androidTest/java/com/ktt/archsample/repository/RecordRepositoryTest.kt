package com.ktt.archsample.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
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
        val context = InstrumentationRegistry.getTargetContext()
        val component = DaggerRepositoryComponent.builder()
                .repositoryModule(RepositoryTestModule(context.applicationContext))
                .build()
        repository = RecordRepository(component.provideContext(), component.provideDatabaseCreator())
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

//    @Test
//    fun saveRecord() {
//        val record = TestUtil.newRecord()
//
//        repository.saveRecord(record)
//
//        verify(repository.dao).save(record)
//    }
}
