package com.ktt.archsample.repository

import com.ktt.archsample.App
import com.ktt.archsample.dao.RecordDao
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
@Module
class RepositoryModule {
    @Provides
    fun provideRepository(dao: RecordDao, executor: Executor): RecordRepository {
        return RecordRepository(dao, executor)
    }

    @Provides
    fun provideDao(): RecordDao {
        return App.dataBase!!.recordDao()
    }

    @Provides
    fun provideExecutor(): Executor {
        return App.executor!!
    }
}