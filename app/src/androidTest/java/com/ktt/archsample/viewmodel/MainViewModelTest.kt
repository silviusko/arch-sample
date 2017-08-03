package com.ktt.archsample.viewmodel

import android.app.Application
import android.support.test.annotation.UiThreadTest
import android.support.test.runner.AndroidJUnit4
import com.ktt.archsample.repository.DaggerRepositoryComponent
import com.ktt.archsample.repository.RepositoryTestModule
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(Mockito.mock(Application::class.java))

        DaggerRepositoryComponent.builder()
                .repositoryModule(RepositoryTestModule())
                .build()
                .inject(viewModel)
    }

    //FIXME: the repository is incorrect to be injected
    @Test
    @UiThreadTest
    fun dice() {
        viewModel.dice()

        Mockito.verify(viewModel.repository).dice(viewModel)
    }

}