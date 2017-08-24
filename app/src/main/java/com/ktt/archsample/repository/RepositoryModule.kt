package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import dagger.Module
import dagger.Provides

/**
 * @author luke_kao
 */
@Module
open class RepositoryModule(val context: Context) {

    @Provides
    fun provideRepository(context: Context, creator: DatabaseCreator): RecordRepository {
        return newRecordRepository(context, creator)
    }

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideDbCreator(): DatabaseCreator {
        return newDbCreator()
    }

    open fun newRecordRepository(context: Context, creator: DatabaseCreator) = RecordRepository(context, creator)
    open fun newDbCreator() = DatabaseCreator.instance
}