package com.ktt.archsample.repository

import com.ktt.archsample.db.dao.RecordDao
import com.ktt.archsample.viewmodel.MainViewModel
import dagger.Component
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
@Component(modules = arrayOf(RepositoryModule::class))
interface RepositoryComponent {

    fun provideRepository(): RecordRepository

    fun provideRecordDao(): RecordDao

    fun provideExecutor(): Executor

    fun inject(viewModel: MainViewModel)
}