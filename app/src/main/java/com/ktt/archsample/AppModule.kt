package com.ktt.archsample

import com.ktt.archsample.db.DatabaseCreator
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * @author luke_kao
 */
@Module
open class AppModule {
    @Provides
    @Singleton
    fun provideDbCreator(executor: Executor): DatabaseCreator {
        return newDbCreator(executor)
    }

    @Provides
    @Singleton
    fun provideExecutor(): Executor {
        return newExecutor()
    }

    open fun newDbCreator(executor: Executor) = DatabaseCreator(executor)
    open fun newExecutor(): Executor = Executors.newFixedThreadPool(5)
}