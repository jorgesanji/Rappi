package com.cronosgroup.core.rest;

import android.content.Context;

import com.cronosgroup.core.BuildConfig;
import com.cronosgroup.core.managers.AssetsManager;
import com.google.gson.Gson;


/**
 * Created by jorgesanmartin on 1/28/16.
 */
public class RestConfig implements ConfigRestService {

    private final Context context;
    private final Config config;

    public RestConfig(Context application) {
        this.context = application;
        this.config = getRestconfig();
    }

    private Config getRestconfig() {
        Config config = null;
        try {
            config = new Gson().fromJson(AssetsManager.getAssetFromFilePath(context, "config/config.json"), Config.class);
        } finally {
            return config;
        }
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public String getUrlByMode(Mode type) {
        switch (type) {
            case QA:
                return config.getQa();
            case PRODUCTION:
                return config.getProd();
            default:
                return config.getDev();
        }
    }

    @Override
    public Mode getCurrentMode() {
        if (BuildConfig.FLAVOR.equalsIgnoreCase(Mode.QA.getMode()))
            return Mode.QA;
        else if (BuildConfig.FLAVOR.equalsIgnoreCase(Mode.DEVELOP.getMode()))
            return Mode.DEVELOP;
        else
            return Mode.PRODUCTION;
    }

    @Override
    public String getCurrentUrl() {
        if (BuildConfig.FLAVOR.equalsIgnoreCase(Mode.QA.getMode()))
            return getUrlByMode(Mode.QA);
        else if (BuildConfig.FLAVOR.equalsIgnoreCase(Mode.DEVELOP.getMode()))
            return getUrlByMode(Mode.DEVELOP);
        else
            return getUrlByMode(Mode.PRODUCTION);
    }

    private class Config {
        private String dev;
        private String qa;
        private String prod;

        public String getDev() {
            return dev;
        }

        public void setDev(String dev) {
            this.dev = dev;
        }

        public String getQa() {
            return qa;
        }

        public void setQa(String qa) {
            this.qa = qa;
        }

        public String getProd() {
            return prod;
        }

        public void setProd(String prod) {
            this.prod = prod;
        }
    }
}
