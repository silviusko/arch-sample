package com.ktt.archsample.task

import android.support.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import java.util.concurrent.CountDownLatch

/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class DiceGeneratorTest {
    @Test
    fun runDiceGenerator() {
        val countdownLatch = CountDownLatch(1)

        val callback = mock(DiceGenerator.Callback::class.java)
        `when`(callback.updateResult(anyInt())).then { countdownLatch.countDown() }

        val task = DiceGenerator(callback)

        task.execute()

        countdownLatch.await()

        verify(callback, times(11)).updateProgress(anyInt(), anyInt())
        verify(callback).updateResult(anyInt())
    }
}
