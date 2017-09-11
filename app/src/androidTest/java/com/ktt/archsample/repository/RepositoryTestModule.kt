package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import org.mockito.Mockito.mock
import org.mockito.Mockito.withSettings
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
class RepositoryTestModule(context: Context) : RepositoryModule(context) {

    override fun newRecordRepository(context: Context, creator: DatabaseCreator, executor: Executor): RecordRepository {
        return mock(RecordRepository::class.java, withSettings().useConstructor(context, creator, executor))
    }
}