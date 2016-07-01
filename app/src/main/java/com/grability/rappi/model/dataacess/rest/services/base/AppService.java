package com.grability.rappi.model.dataacess.rest.services.base;

import com.cronosgroup.core.rest.Callback;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.grability.rappi.model.dataacess.rest.manager.AppRestManager;
import com.grability.rappi.model.dataacess.rest.mapping.JsonToModel;

import java.lang.reflect.Type;

/**
 * Created by jorgesanmartin on 14/10/15.
 */
public class AppService {

    // Request

    public static void GET(String url, Class clazz, Callback callback, Object tag) {
        AppRestManager.GET(url, clazz, callback, tag);
    }

    // Mapping Json to Model

    protected static Object mappingJson(JsonElement jsonObject, Class clazz) {
        return AppRestManager.sharedManager().mapping.mappingJson(jsonObject, clazz);
    }

    protected static Object mappingJson(JsonObject jsonObject, Class clazz) {
        return AppRestManager.sharedManager().mapping.mappingJson(jsonObject, clazz);
    }

    protected static Object mappingJson(String stringJson, Class clazz) {
        return AppRestManager.sharedManager().mapping.mappingJson(stringJson, clazz);
    }

    protected static Object mappingJson(String stringJson, String rootKey, Class clazz) {
        return AppRestManager.sharedManager().mapping.mappingJson(stringJson, rootKey, clazz);
    }

    protected static Object mappingJson(String stringJson, String rootKey, Type type) {
        return AppRestManager.sharedManager().mapping.mappingJson(stringJson, rootKey, type);
    }

    protected static void mappingJsonInBackground(JsonObject jsonObject, Class clazz, JsonToModel.Listener callback) {
        AppRestManager.sharedManager().mapping.mappingJsonInBackground(jsonObject, clazz, callback);
    }

    protected static void mappingJsonInBackground(String stringJson, Class clazz, JsonToModel.Listener callback) {
        AppRestManager.sharedManager().mapping.mappingJsonInBackground(stringJson, clazz, callback);
    }

    protected static <T> void mappingListInBackground(String json, String rootPath, Class<T> model, JsonToModel.Listener callback) {
        AppRestManager.sharedManager().mapping.mappingListInBackground(json, rootPath, model, callback);
    }
}
