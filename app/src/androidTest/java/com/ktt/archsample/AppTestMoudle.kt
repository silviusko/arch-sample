package com.ktt.archsample

import com.ktt.archsample.db.DatabaseCreator
import org.mockito.Mockito
import java.util.concurrent.Executor

/**
 * @author luke_kao
 */
class AppTestMoudle : AppModule() {

    override fun newDbCreator(executor: Executor): DatabaseCreator {
        return Mockito.mock(DatabaseCreator::class.java, Mockito.withSettings().useConstructor(executor))
    }

    override fun newExecutor(): Executor {
        return Mockito.mock(Executor::class.java)
    }
}