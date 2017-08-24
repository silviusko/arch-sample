package com.ktt.archsample.repository

import android.content.Context
import com.ktt.archsample.db.DatabaseCreator
import dagger.Module
import org.mockito.Mockito

/**
 * @author luke_kao
 */
@Module
class RepositoryTestModule(context: Context) : RepositoryModule(context) {

    override fun newRecordRepository(context: Context, creator: DatabaseCreator): RecordRepository {
        return Mockito.mock(RecordRepository::class.java, Mockito.withSettings().useConstructor(context, creator))
    }

    override fun newDbCreator(): DatabaseCreator {
        return Mockito.mock(DatabaseCreator::class.java)
    }
}