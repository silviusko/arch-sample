package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @author luke_kao
 */
@Module
open class RepositoryModule(val context: Context) {

    @Provides
    fun provideRepository(context: Context, creator: DatabaseCreator, executor: Executor): RecordRepository {
        return newRecordRepository(context, creator, executor)
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideDbCreator(executor: Executor): DatabaseCreator {
        return newDbCreator(executor)
    }

    @Provides
    fun provideExecutor(): Executor {
        return newExecutor()
    }

    open fun newRecordRepository(context: Context, creator: DatabaseCreator, executor: Executor)
            = RecordRepository(context, creator, executor)

    open fun newDbCreator(executor: Executor) = DatabaseCreator(executor)
    open fun newExecutor(): Executor = Executors.newFixedThreadPool(5)
}