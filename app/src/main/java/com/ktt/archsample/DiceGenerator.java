package com.ktt.archsample;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.util.Random;

/**
 * @author luke_kao
 */

public class DiceGenerator extends AsyncTask<Void, Integer, Integer> {
    public interface Callback {
        void updateProgress(int progress);

        void updateResult(int result);
    }

    private final Callback mCallback;

    public DiceGenerator(Callback callback) {
        mCallback = callback;
    }


    @Override
    protected Integer doInBackground(Void... params) {
        Random random = new Random(SystemClock.currentThreadTimeMillis());

        for (int i = 0; i < 100; i++) {
            publishProgress(i);
            SystemClock.sleep(random.nextInt(15) + 15);
        }

        return random.nextInt(6) + 1;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        if (mCallback != null) {
            mCallback.updateProgress(values[0]);
        }

    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        if (mCallback != null) {
            mCallback.updateResult(integer);
        }
    }
}
