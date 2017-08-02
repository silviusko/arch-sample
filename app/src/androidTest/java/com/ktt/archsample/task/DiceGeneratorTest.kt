package com.ktt.archsample.task

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch

/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class DiceGeneratorTest {
    @Test
    fun runDiceGenerator() {
        val countdownLatch = CountDownLatch(1)

        val callback = Mockito.mock(DiceGenerator.Callback::class.java)
        Mockito.`when`(callback.updateResult(Mockito.anyInt())).then { countdownLatch.countDown() }

        val task = DiceGenerator(callback)

        task.execute()

        countdownLatch.await()

        Mockito.verify(callback, Mockito.times(100)).updateProgress(Mockito.anyInt())
        Mockito.verify(callback).updateResult(Mockito.anyInt())
    }
}
