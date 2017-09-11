package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
@Module
open class RepositoryModule(val context: Context) {
    @Provides
    @RepositoryScope
    fun provideRepository(context: Context, creator: DatabaseCreator, executor: Executor): RecordRepository {
        return newRecordRepository(context, creator, executor)
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    open fun newRecordRepository(context: Context, creator: DatabaseCreator, executor: Executor)
            = RecordRepository(context, creator, executor)
}