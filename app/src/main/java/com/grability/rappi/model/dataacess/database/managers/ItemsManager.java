package com.grability.rappi.model.dataacess.database.managers;

import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.model.dataacess.database.repository.AppItemRepository;
import com.grability.rappi.model.dataacess.rest.model.RestEntry;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by jorgesanmartin on 7/4/16.
 */
public class ItemsManager {

    private AppItemRepository appItemRepository;

    public ItemsManager() {
        this.appItemRepository = new AppItemRepository();
    }

    public void saveItemsAndCategories(RestEntry[] entries) {

        if (entries.length != 0) {
            // Remove older apps
            appItemRepository.deleteAll();

            // Add new apps
            for (RestEntry entry : entries) {
                appItemRepository.create(AppItem.class, entry);
            }
        }
    }

    public List<AppItem> getAllItemOrderByName() {
        RealmResults<AppItem> items = appItemRepository.getAllItems();
        items.sort("name");
        return items;
    }

}
