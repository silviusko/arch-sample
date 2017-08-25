package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import com.ktt.archsample.viewmodel.MainViewModel
import dagger.Component
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
@Component(modules = arrayOf(RepositoryModule::class))
interface RepositoryComponent {

    fun provideRepository(): RecordRepository

    fun provideContext(): Context

    fun provideDatabaseCreator(): DatabaseCreator

    fun provideExecutor(): Executor

    fun inject(viewModel: MainViewModel)
}