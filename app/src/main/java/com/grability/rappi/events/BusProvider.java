package com.grability.rappi.events;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by jorgesanmartin on 3/7/16.
 */
public class BusProvider {

    private static BusProvider busProvider;
    private final static long DELAY_BUS = 3750;
    private Bus bus;
    private Handler mHandler = new Handler(Looper.myLooper());

    public static BusProvider getInstance() {
        if (busProvider == null) {
            busProvider = new BusProvider();
            busProvider.setBus(new Bus(ThreadEnforcer.MAIN));
        }

        return busProvider;
    }

    private BusProvider() {
    }

    private void setBus(Bus bus) {
        this.bus = bus;
    }

    public void postWithDelay(final Object object) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bus.post(object);
            }
        }, DELAY_BUS);
    }

    public void post(Object object) {
        bus.post(object);
    }

    public void register(Object object) {
        bus.register(object);
    }

    public void unRegister(Object object) {
        bus.unregister(object);
    }
}
