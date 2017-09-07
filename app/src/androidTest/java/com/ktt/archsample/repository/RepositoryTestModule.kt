package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import dagger.Module
import org.mockito.Mockito
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
@Module
class RepositoryTestModule(context: Context) : RepositoryModule(context) {

    override fun newRecordRepository(context: Context, creator: DatabaseCreator, executor: Executor): RecordRepository {
        return Mockito.mock(RecordRepository::class.java, Mockito.withSettings().useConstructor(context, creator, executor))
    }

    override fun newDbCreator(): DatabaseCreator {
        return Mockito.mock(DatabaseCreator::class.java)
    }

    override fun newExecutor(): Executor {
        return Mockito.mock(Executor::class.java)
    }
}