package com.grability.rappi.model.dataacess.rest.manager;

import android.content.Context;

import com.android.volley.VolleyLog;
import com.cronosgroup.core.rest.Callback;
import com.cronosgroup.core.rest.ConfigRestService;
import com.cronosgroup.core.rest.RestManager;
import com.cronosgroup.core.rest.SessionStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grability.rappi.model.dataacess.rest.mapping.JsonToModel;

/**
 * Created by jorgesanmartin on 15/10/15.
 */

public class AppRestManager extends RestManager {

    private static String FORMATT_DATE = "yyyy-MM-dd H:mm:ss.s Z";
    private static AppRestManager manager = null;

    // Public Methods

    public static JsonToModel mapping = new JsonToModel(AppRestManager.getMapperUsed());

    public static synchronized AppRestManager sharedManager() {
        if (manager == null) {
            throw new IllegalArgumentException("please set to configureRestManager");
        }
        return manager;
    }

    public static void configureRestManager(ConfigRestService configRestService, SessionStatus listener) {
        if (configRestService != null) {
            manager = new AppRestManager(configRestService.getContext(), configRestService.getCurrentUrl());
            manager.setSessionStatusListener(listener);
            manager.setMapper(getMapperUsed());
            VolleyLog.DEBUG = (configRestService.getCurrentMode() == ConfigRestService.Mode.DEVELOP) || (configRestService.getCurrentMode() == ConfigRestService.Mode.QA);
        }
    }

    public AppRestManager(Context context, String baseUrl) {
        super(context, baseUrl);
    }

    public static Gson getMapperUsed() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(FORMATT_DATE);
        gsonBuilder.registerTypeAdapter(String.class, new JsonToModel.StringDeserializer());
        Gson mGson = gsonBuilder.create();

        return mGson;
    }

    public static void GET(String url, Class clazz, Callback callback, Object tag) {
        AppRestManager.sharedManager().getRequest(url, clazz, callback, tag);
    }

}
