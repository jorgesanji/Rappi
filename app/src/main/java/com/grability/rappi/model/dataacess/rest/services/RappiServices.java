package com.grability.rappi.model.dataacess.rest.services;

import com.cronosgroup.core.rest.Callback;
import com.cronosgroup.core.rest.RestError;
import com.grability.rappi.model.dataacess.rest.model.RestResponse;
import com.grability.rappi.model.dataacess.rest.services.base.AppService;

/**
 * Created by jorgesanmartin on 11/17/15.
 */
public class RappiServices extends AppService {

    private static final String URL_ITEMS = "us/rss/topfreeapplications/limit=20/json";

    public static <T extends RestResponse> void getRappiItems(final Class<T> clazz, final Callback callback, Object tag) {

        GET(URL_ITEMS, clazz, new Callback<RestResponse, RestError>() {
            @Override
            public void onResponse(RestResponse response) {
                if (callback != null) {
                    if (response != null) {
                        callback.onResponse(response);
                    } else {
                        callback.onErrorResponse(RestError.getRequestError());
                    }
                }
            }

            @Override
            public void onErrorResponse(RestError error) {
                if (callback != null) {
                    callback.onErrorResponse(error);
                }
            }

        }, tag);
    }
}
