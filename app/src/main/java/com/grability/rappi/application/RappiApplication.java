package com.grability.rappi.application;

import com.cronosgroup.core.managers.AnalyticsManager;
import com.cronosgroup.core.view.application.BaseApplication;
import com.grability.rappi.R;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class RappiApplication extends BaseApplication {

    private static RappiApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        init();
    }

    private void init() {
        initAnalytics();
    }

    private void initAnalytics() {
        AnalyticsManager.initTracker(this, R.string.google_analytics_key);
    }

    @Override
    public void onBackground() {

    }

    @Override
    public void onForeground() {

    }

    public static RappiApplication getApp() {
        return app;
    }

}
