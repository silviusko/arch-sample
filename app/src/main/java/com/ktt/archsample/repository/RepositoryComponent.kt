package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.AppComponent
import com.ktt.archsample.viewmodel.MainViewModel
import dagger.Component

/**
 * @author luke_kao
 */
@RepositoryScope
@Component(
        modules = arrayOf(RepositoryModule::class),
        dependencies = arrayOf(AppComponent::class)
)
interface RepositoryComponent {

    fun provideRepository(): RecordRepository

    fun provideContext(): Context

    fun inject(viewModel: MainViewModel)
}