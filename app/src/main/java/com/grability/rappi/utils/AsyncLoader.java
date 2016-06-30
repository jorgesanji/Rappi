package com.grability.rappi.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by jorgesanmartin on 10/30/15.
 */
public abstract class AsyncLoader<T> extends Thread {

    public abstract T doInBackground();

    public abstract void postProcess(T result);

    private Handler mHandler = new Handler(Looper.myLooper());

    @Override
    public void run() {
        super.run();
        final Object object = doInBackground();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                postProcess((T) object);
            }
        });
    }

    public void UIUpdate(Runnable runnable) {
        mHandler.post(runnable);
    }
}
