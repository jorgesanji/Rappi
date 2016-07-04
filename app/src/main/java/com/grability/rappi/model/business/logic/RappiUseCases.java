package com.grability.rappi.model.business.logic;

import com.cronosgroup.core.rest.Callback;
import com.cronosgroup.core.rest.RestError;
import com.grability.rappi.model.dataacess.database.managers.CategoryManager;
import com.grability.rappi.model.dataacess.database.managers.ItemsManager;
import com.grability.rappi.model.dataacess.rest.model.RestResponse;
import com.grability.rappi.model.dataacess.rest.services.RappiServices;


/**
 * Created by jorgesanmartin on 15/10/15.
 */
public class RappiUseCases {

    public static void getItems(final Callback callback, Object tag) {
        RappiServices.getRappiItems(RestResponse.class, new Callback<RestResponse, RestError>() {
            @Override
            public void onResponse(RestResponse response) {

                ItemsManager itemsManager = new ItemsManager();
                itemsManager.saveItemsAndCategories(response.getFeed().getEntry());
                // Return with callback
                callback.onResponse(new CategoryManager().getAllItemOrderByName());
            }

            @Override
            public void onErrorResponse(RestError error) {

            }
        }, tag);
    }
}
