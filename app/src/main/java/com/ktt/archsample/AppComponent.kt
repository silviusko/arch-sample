package com.ktt.archsample

import com.ktt.archsample.db.DatabaseCreator
import dagger.Component
import java.util.concurrent.Executor
import javax.inject.Singleton

/**
 * @author luke_kao
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun provideDatabaseCreator(): DatabaseCreator

    fun provideExecutor(): Executor
}