package com.ktt.archsample.repository

import com.ktt.archsample.dao.RecordDao
import dagger.Module
import dagger.Provides
import org.mockito.Mockito
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
        return Mockito.mock(RecordDao::class.java)
    }

    @Provides
    fun provideExecutor(): Executor {
        return DirectRunExecutor()
    }
}

private class DirectRunExecutor : Executor {
    override fun execute(runnable: Runnable?) {
        runnable?.run()
    }
}