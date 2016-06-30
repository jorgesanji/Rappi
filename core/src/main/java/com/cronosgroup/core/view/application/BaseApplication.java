package com.cronosgroup.core.view.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.cronosgroup.core.rest.ConfigRestService;
import com.cronosgroup.core.rest.RestConfig;


/**
 * Created by jorgesanmartin on 12/1/15.
 */
public abstract class BaseApplication extends MultiDexApplication implements ApplicationState.Listener {

    private ConfigRestService config;
    private boolean firstLoadApplication = false;

    public abstract void onBackground();

    public abstract void onForeground();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLifeCyclerListener();
        initConfig();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ApplicationState.getInstance().unBind();
    }

    private void initConfig() {
        config = new RestConfig(getApplicationContext());
    }

    private void initLifeCyclerListener() {
        ApplicationState.init(this).addApplicationStateListener(this);
    }

    public ConfigRestService getConfig() {
        return config;
    }

    @Override
    public void toBackground() {
        onBackground();
    }

    @Override
    public void toForeground() {
        onForeground();
    }

    public boolean isFirstLoadApplication() {
        return firstLoadApplication;
    }

    public void setFirstLoadApplication(boolean firstLoadApplication) {
        this.firstLoadApplication = firstLoadApplication;
    }
}
