package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import dagger.Module
import org.mockito.Mockito.mock
import org.mockito.Mockito.withSettings
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
@Module
class RepositoryTestModule(context: Context) : RepositoryModule(context) {

    override fun newRecordRepository(context: Context, creator: DatabaseCreator, executor: Executor): RecordRepository {
        return mock(RecordRepository::class.java, withSettings().useConstructor(context, creator, executor))
    }

    override fun newDbCreator(executor: Executor): DatabaseCreator {
        return mock(DatabaseCreator::class.java, withSettings().useConstructor(executor))
    }

    override fun newExecutor(): Executor {
        return mock(Executor::class.java)
    }
}