package com.ktt.archsample.repository

import androidx.test.InstrumentationRegistry
import androidx.test.annotation.UiThreadTest
import androidx.test.runner.AndroidJUnit4
import com.ktt.archsample.AppTestMoudle
import com.ktt.archsample.DaggerAppComponent
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

        val appComponent = DaggerAppComponent.builder()
                .appModule(AppTestMoudle())
                .build()

        val component = DaggerRepositoryComponent.builder()
                .appComponent(appComponent)
                .repositoryModule(RepositoryTestModule(context))
                .build()

        repository = RecordRepository(
                component.provideContext(),
                appComponent.provideDatabaseCreator(),
                appComponent.provideExecutor()
        )
    }

    @Test
    @UiThreadTest
    fun loadRecords() {
        repository.loadRecords()

        verify(repository.dbCreator).create(repository.context)
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
