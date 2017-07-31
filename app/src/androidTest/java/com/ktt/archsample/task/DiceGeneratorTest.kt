package com.ktt.archsample.task

import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

/**
 * @author luke_kao
 */
@RunWith(AndroidJUnit4::class)
class DiceGeneratorTest {
    @Test
    fun runDiceGenerator() {
        val callback = MockCallback()
        val task = DiceGenerator(callback)

        task.execute()

        callback.countdownLatch.await()

        Assert.assertEquals(100, callback.updateProgressCount)
        Assert.assertEquals(1, callback.updateResultCount)
    }
}

class MockCallback : DiceGenerator.Callback {
    val countdownLatch = CountDownLatch(1)
    var updateProgressCount = 0
    var updateResultCount = 0

    override fun updateProgress(progress: Int?) {
        updateProgressCount++
    }

    override fun updateResult(result: Int?) {
        updateResultCount++
        countdownLatch.countDown()
    }

}
