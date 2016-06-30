package com.grability.rappi.model.rest.dataacess.rest.services;

import com.cronosgroup.core.rest.Callback;
import com.cronosgroup.core.rest.RestError;
import com.grability.rappi.model.rest.dataacess.rest.model.RestResponse;
import com.grability.rappi.model.rest.dataacess.rest.services.base.AppService;

/**
 * Created by jorgesanmartin on 11/17/15.
 */
public class RappiServices extends AppService {

    private static final String URL_CONFIG_DATA = "api/configData";

    public static <T extends RestResponse> void getRappiItems(final Class<T> clazz, final Callback callback, Object tag) {

        GET(URL_CONFIG_DATA, new Callback<RestResponse, RestError>() {
            @Override
            public void onResponse(RestResponse response) {
                if (callback != null) {
//                    if (response != null && !response.getError().hasError()) {
//                        mappingJsonInBackground(response.getData(), clazz, new JsonToModel.Listener<Object, RestError>() {
//                            @Override
//                            public void mappingFinished(Object response) {
//                                if (callback != null) {
//                                    callback.onResponse(response);
//                                }
//                            }
//
//                            @Override
//                            public void errorMagpping(RestError error) {
//                                if (callback != null) {
//                                    callback.onErrorResponse(error);
//                                }
//                            }
//                        });
//                    } else {
//                        callback.onErrorResponse(RestError.getRequestError());
//                    }
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
