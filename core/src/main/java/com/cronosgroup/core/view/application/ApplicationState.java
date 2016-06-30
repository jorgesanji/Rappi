package com.cronosgroup.core.view.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.cronosgroup.core.view.BaseActivity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by jorgesanmartin on 2/11/16.
 */
public class ApplicationState implements Application.ActivityLifecycleCallbacks {

    public interface Listener {
        void toBackground();

        void toForeground();
    }

    private static long INTERVAL_STATUS = 1000;
    private List<Listener> listeners = new CopyOnWriteArrayList<>();
    private List<BaseActivity> activities = new CopyOnWriteArrayList<>();
    private Handler mHandler = new Handler(Looper.myLooper());
    private boolean inBackground = true;
    private Runnable taskActiveApplications = new Runnable() {
        @Override
        public void run() {
            isApplicationActive();
        }
    };

    private static ApplicationState instance;

    public static ApplicationState init(Application application) {
        if (instance == null) {
            instance = new ApplicationState();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static ApplicationState getInstance() {

        if (instance == null) {
            throw new IllegalStateException(
                    "ApplicationState is not initialised - first invocation must use parameterised init");
        }

        return instance;
    }

    private void reviewApplicationState() {
        mHandler.removeCallbacks(taskActiveApplications);
        mHandler.postDelayed(taskActiveApplications, INTERVAL_STATUS);
    }

    private void isApplicationActive() {
        for (BaseActivity activity : activities) {
            if (activity.isActive()) {
                inBackground = false;
                return;
            }
        }

        inBackground = true;
        for (Listener listener : listeners) {
            listener.toBackground();
        }
    }

    private void validateApplicationState() {
        if (!listeners.isEmpty() && inBackground) {
            inBackground = false;
            for (Listener listener : listeners) {
                listener.toForeground();
            }
        }
    }

    // Public methods

    public void addApplicationStateListener(Listener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeApplicationStateListener(Listener listener) {
        if (listeners.contains(listener)) {
            listeners.remove(listener);
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity instanceof BaseActivity) {
            activities.add((BaseActivity) activity);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).setActive(true);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

        validateApplicationState();

        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).setActive(true);
        }

        reviewApplicationState();
    }

    @Override
    public void onActivityPaused(Activity activity) {
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).setActive(false);
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).setActive(false);
        }
        reviewApplicationState();
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity instanceof BaseActivity) {
            activities.remove(activity);
        }
    }

    public void unBind() {
        mHandler.removeCallbacks(taskActiveApplications);
        listeners.clear();
        activities.clear();
    }
}
