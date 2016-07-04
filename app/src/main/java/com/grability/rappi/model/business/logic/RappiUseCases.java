package com.grability.rappi.model.business.logic;

import com.cronosgroup.core.rest.Callback;
import com.cronosgroup.core.rest.RestError;
import com.grability.rappi.model.dataacess.database.model.AppCategory;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.model.dataacess.rest.model.RestEntry;
import com.grability.rappi.model.dataacess.rest.model.RestResponse;
import com.grability.rappi.model.dataacess.rest.services.RappiServices;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;


/**
 * Created by jorgesanmartin on 15/10/15.
 */
public class RappiUseCases {

    public static void getItems(final Callback callback, Object tag) {
        RappiServices.getRappiItems(RestResponse.class, new Callback<RestResponse, RestError>() {
            @Override
            public void onResponse(RestResponse response) {

                List<AppItem> appItemList = new ArrayList<AppItem>();

                Realm realm = Realm.getDefaultInstance();

                if (response.getFeed().getEntry().length != 0) {

                    // Remove older apps
                    realm.beginTransaction();
                    realm.clear(AppItem.class);
                    realm.clear(AppCategory.class);
                    realm.commitTransaction();

                    // Add new apps
                    for (RestEntry entry : response.getFeed().getEntry()) {
                        realm.beginTransaction();

                        AppItem appItem = realm.createObject(AppItem.class);
                        appItem.setName(entry.name.label);
                        appItem.setId(entry.id.attributes.id);
                        appItem.setUrl(entry.link.attributes.href);
                        appItem.setDescription(entry.summary.label);
                        appItem.setTitle(entry.title.label);
                        appItem.setPrice(entry.price.attributes.amount + entry.price.attributes.currency);

                        if (entry.image.length > 0) {
                            appItem.setImage(entry.image[0].label);
                            appItem.setThumbImage(entry.image[entry.image.length - 1].label);
                        }

                        AppCategory appCategory = realm.createObject(AppCategory.class);
                        appCategory.setId(entry.category.attributes.id);
                        appCategory.setTerm(entry.category.attributes.term);
                        appCategory.setScheme(entry.category.attributes.scheme);
                        appCategory.setLabel(entry.category.attributes.label);
                        appItem.setCategory(appCategory);

                        appItemList.add(appItem);

                        realm.copyToRealm(appItem);
                        realm.commitTransaction();
                    }
                } else {
                    appItemList = realm.where(AppItem.class).findAll();
                }

                // Return with callback
                callback.onResponse(appItemList);
            }

            @Override
            public void onErrorResponse(RestError error) {

            }
        }, tag);
    }
}
