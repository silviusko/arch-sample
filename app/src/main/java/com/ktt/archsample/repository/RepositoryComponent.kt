package com.ktt.archsample.repository

import com.ktt.archsample.viewmodel.MainViewModel
import dagger.Component

/**
 * @author luke_kao
 */
@Component(modules = arrayOf(RepositoryModule::class))
interface RepositoryComponent {

    fun provideRepository(): RecordRepository

    fun inject(viewModel: MainViewModel)
}