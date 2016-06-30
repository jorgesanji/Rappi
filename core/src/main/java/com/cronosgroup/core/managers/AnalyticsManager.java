package com.cronosgroup.core.managers;

import android.content.Context;

import com.cronosgroup.core.exceptions.AnalyticsException;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by jorgesanmartin on 2/5/16.
 */
public class AnalyticsManager {

    private Tracker mTracker;
    private final Context context;
    private static AnalyticsManager mInstance;

    public static void initTracker(Context context, int idTracker) {
        if (mInstance == null) {
            mInstance = new AnalyticsManager(context, idTracker);
        }
    }

    public static AnalyticsManager getInstance() {
        if (mInstance == null) {
            try {
                throw new AnalyticsException("Error must be initialized before");
            } catch (AnalyticsException e) {
                e.printStackTrace();
            }
        }
        return mInstance;
    }

    protected AnalyticsManager(Context context, int idTracker) {
        this.context = context;
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
        mTracker = analytics.newTracker(idTracker);
    }

    // Public methods

    public void trackScreen(String screen) {
        mTracker.setScreenName(screen);
    }

    public void trackAction(String category, String actionUi, String description, String nameScreen) {
        HitBuilders.EventBuilder builder = new HitBuilders.EventBuilder();
        if (category != null && !category.isEmpty()) {
            builder.setCategory(category);
        }

        if (actionUi != null && !actionUi.isEmpty()) {
            builder.setAction(actionUi);
        }

        if (description != null && !description.isEmpty()) {
            builder.setLabel(description);
        }

        if (nameScreen != null && nameScreen.isEmpty()) {
            mTracker.setScreenName(nameScreen);
        }

        mTracker.send(builder.build());
    }
}
