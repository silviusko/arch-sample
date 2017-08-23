package com.ktt.archsample.viewmodel

import android.app.Application
import android.support.test.annotation.UiThreadTest
import android.support.test.runner.AndroidJUnit4
import com.ktt.archsample.repository.DaggerRepositoryComponent
import com.ktt.archsample.repository.RepositoryTestModule
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(mock(Application::class.java))

        DaggerRepositoryComponent.builder()
                .repositoryModule(RepositoryTestModule())
                .build()
                .inject(viewModel)
    }

    @Test
    fun refresh() {
        viewModel.refreshRecords()

        verify(viewModel.repository).getRecords()
    }

    @Test
    @UiThreadTest
    fun dice() {
        viewModel.dice()

        Assert.assertEquals(0, viewModel.progressLiveData.value)
        Assert.assertEquals(0, viewModel.resultLiveData.value)
        verify(viewModel.repository).dice(viewModel)
    }

    @Test(expected = KotlinNullPointerException::class)
    @UiThreadTest
    fun updateProgress_null() {
        viewModel.updateProgress(null, null)
    }

    @Test
    @UiThreadTest
    fun updateProgress_integer() {
        viewModel.updateProgress(123, 124)
        Assert.assertEquals(123, viewModel.progressLiveData.value)
        Assert.assertEquals(124, viewModel.resultLiveData.value)
    }

    @Test(expected = KotlinNullPointerException::class)
    @UiThreadTest
    fun updateResult_null() {
        viewModel.updateResult(null)
    }

    @Test
    @UiThreadTest
    fun updateResult_integer() {
        viewModel.updateResult(123)

        Assert.assertEquals(123, viewModel.resultLiveData.value)

        //FIXME: the any() or captor is not work for kotlin
//        val recordCaptor = ArgumentCaptor.forClass(Record::class.java)
//        Mockito.verify(viewModel.repository).saveRecord(recordCaptor.capture())
//        Assert.assertEquals(123, recordCaptor.value)

        //FIXME: UnfinishedVerificationException: Missing method call for verify(mock)
        // refreshRecords()
//        Mockito.verify(viewModel.repository).getRecords(viewModel)
    }
}