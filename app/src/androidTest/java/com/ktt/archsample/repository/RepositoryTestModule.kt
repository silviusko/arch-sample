package com.ktt.archsample.repository

import com.ktt.archsample.db.dao.RecordDao
import dagger.Module
import org.mockito.Mockito
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
@Module
class RepositoryTestModule : RepositoryModule() {

    override fun newRecordRepository(dao: RecordDao, executor: Executor): RecordRepository {
        return Mockito.mock(RecordRepository::class.java, Mockito.withSettings().useConstructor(dao, executor))
    }

    override fun newRecordDao(): RecordDao {
        return Mockito.mock(RecordDao::class.java)
    }

    override fun newExecutor(): Executor {
        return Executor {
            it?.run()
        }
    }
}