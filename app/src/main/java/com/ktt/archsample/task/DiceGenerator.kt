package com.ktt.archsample.task

import android.os.AsyncTask
import android.os.SystemClock
import java.util.*

/**
 * @author luke_kao
 */

class DiceGenerator(private val mCallback: Callback?) : AsyncTask<Void, Int, Int>() {
    interface Callback {
        fun updateProgress(progress: Int?, fakeResult: Int?)

        fun updateResult(result: Int?)
    }

    val random = Random()
    val diceNum: Int
        get() = random.nextInt(6) + 1

    override fun doInBackground(vararg params: Void): Int? {
        for (i in 0..10) {
            publishProgress(i, diceNum)
            SystemClock.sleep(100)
        }

        return diceNum
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)

        mCallback?.updateProgress(values[0], values[1])
    }

    override fun onPostExecute(integer: Int?) {
        super.onPostExecute(integer)

        mCallback?.updateResult(integer)
    }
}
