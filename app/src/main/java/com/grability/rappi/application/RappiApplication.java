package com.grability.rappi.application;

import com.cronosgroup.core.managers.AnalyticsManager;
import com.cronosgroup.core.view.application.BaseApplication;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.database.RPDatabase;
import com.grability.rappi.model.dataacess.rest.manager.AppRestManager;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
        initRestConfiguration();
        initImageLoader();
        initDatabase();
        buildComponentAndInject();
    }

    public void buildComponentAndInject() {
//        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    private void initAnalytics() {
        AnalyticsManager.initTracker(this, R.string.google_analytics_key);
    }

    private void initRestConfiguration() {
        AppRestManager.configureRestManager(getConfig(), null);
    }

    private void initDatabase() {
        RPDatabase.initDataBase(this);
    }

    private void initImageLoader() {

        final int DEFAULT_IMAGE_WIDTH = 480;
        final int DEFAULT_IMAGE_HEIGHT = 800;
        final int DEFAULT_MEMORY_CACHE = 2 * 1024 * 1024;
        final int DEFAULT_DISK_CACHE = 50 * 1024 * 1024;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT)
                .diskCacheExtraOptions(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT, null)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(DEFAULT_MEMORY_CACHE))
                .memoryCacheSize(DEFAULT_MEMORY_CACHE)
                .diskCacheSize(DEFAULT_DISK_CACHE)
                .build();

        ImageLoader.getInstance().init(config);
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
