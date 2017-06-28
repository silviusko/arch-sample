package com.ktt.archsample

import android.os.AsyncTask
import android.os.SystemClock
import java.util.*

/**
 * @author luke_kao
 */

class DiceGenerator(private val mCallback: Callback?) : AsyncTask<Void, Int, Int>() {
    interface Callback {
        fun updateProgress(progress: Int?)

        fun updateResult(result: Int?)
    }


    override fun doInBackground(vararg params: Void): Int? {
        val random = Random(SystemClock.currentThreadTimeMillis())

        for (i in 0..99) {
            publishProgress(i)
            SystemClock.sleep((random.nextInt(15) + 15).toLong())
        }

        return random.nextInt(6) + 1
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)

        mCallback?.updateProgress(values[0])
    }

    override fun onPostExecute(integer: Int?) {
        super.onPostExecute(integer)

        mCallback?.updateResult(integer)
    }
}
