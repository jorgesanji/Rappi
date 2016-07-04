package com.grability.rappi.model.dataacess.database.repository;

import com.grability.rappi.model.dataacess.database.model.AppCategory;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.model.dataacess.database.repository.domain.items.ItemRepository;
import com.grability.rappi.model.dataacess.rest.model.RestEntry;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by jorgesanmartin on 7/4/16.
 */
public class AppItemRepository implements ItemRepository<AppItem, RestEntry> {

    @Override
    public RealmResults<AppItem> getAllItems() {
        return realm.where(AppItem.class).findAll();
    }

    @Override
    public List<AppItem> getItemsById(String id) {
        return realm.where(AppItem.class).equalTo("category.id", id).findAll();
    }

    @Override
    public void save(AppItem item) {
        realm.beginTransaction();
        realm.copyToRealm(item);
        realm.commitTransaction();
    }

    @Override
    public void deleteAll() {
        realm.beginTransaction();
        realm.clear(AppItem.class);
        realm.clear(AppCategory.class);
        realm.commitTransaction();
    }

    @Override
    public RealmObject create(Class clazz) {
        RealmObject realmObject = realm.createObject(clazz);
        return realmObject;
    }

    @Override
    public AppItem create(Class clazz, RestEntry entry) {
        realm.beginTransaction();

        AppItem appItem = realm.createObject(AppItem.class);
        appItem.setName(entry.name.label);
        appItem.setId(entry.id.attributes.id);
        appItem.setUrl(entry.link.attributes.href);
        appItem.setDescription(entry.summary.label);
        appItem.setTitle(entry.title.label);
        appItem.setPrice(entry.price.attributes.amount);
        appItem.setCurrency(entry.price.attributes.currency);

        if (entry.image.length > 0) {
            appItem.setImage(entry.image[0].label);
            appItem.setThumbImage(entry.image[entry.image.length - 1].label);
        }

        AppCategory appCategory = getItemById(entry.category.attributes.id);

        if (appCategory == null) {
            appCategory = realm.createObject(AppCategory.class);
            appCategory.setId(entry.category.attributes.id);
            appCategory.setTerm(entry.category.attributes.term);
            appCategory.setScheme(entry.category.attributes.scheme);
            appCategory.setLabel(entry.category.attributes.label);
        }

        appItem.setCategory(appCategory);

        realm.commitTransaction();

        return appItem;
    }

    @Override
    public AppCategory getItemById(String id) {
        return realm.where(AppCategory.class).equalTo("id", id).findFirst();
    }
}
