package com.grability.rappi.model.business.logic;

import com.cronosgroup.core.rest.Callback;
import com.grability.rappi.model.dataacess.rest.model.RestResponse;
import com.grability.rappi.model.dataacess.rest.services.RappiServices;


/**
 * Created by jorgesanmartin on 15/10/15.
 */
public class RappiUseCases {

    public static void getItems(final Callback callback, Object tag) {
        RappiServices.getRappiItems(RestResponse.class, callback, tag);
    }
}
