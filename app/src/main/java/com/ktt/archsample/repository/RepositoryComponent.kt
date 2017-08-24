package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import com.ktt.archsample.viewmodel.MainViewModel
import dagger.Component

/**
 * @author luke_kao
 */
@Component(modules = arrayOf(RepositoryModule::class))
interface RepositoryComponent {

    fun provideRepository(): RecordRepository

    fun provideContext(): Context

    fun provideDatabaseCreator(): DatabaseCreator

    fun inject(viewModel: MainViewModel)
}