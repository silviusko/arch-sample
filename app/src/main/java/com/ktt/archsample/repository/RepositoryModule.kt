package com.ktt.archsample.repository

import com.ktt.archsample.App
import com.ktt.archsample.db.dao.RecordDao
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
@Module
open class RepositoryModule {
    @Provides
    fun provideRepository(dao: RecordDao, executor: Executor): RecordRepository {
        return newRecordRepository(dao, executor)
    }

    @Provides
    fun provideDao(): RecordDao {
        return newRecordDao()
    }

    @Provides
    fun provideExecutor(): Executor {
        return newExecutor()
    }

    open fun newRecordRepository(dao: RecordDao, executor: Executor) = RecordRepository(dao, executor)
    open fun newRecordDao() = App.dataBase!!.recordDao()
    open fun newExecutor() = App.executor!!
}