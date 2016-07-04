package com.grability.rappi.model.dataacess.database.repository.domain.base;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by jorgesanmartin on 7/4/16.
 */
public interface IORepository<T extends RealmObject> {

    Realm realm = Realm.getDefaultInstance();

    List<T> getAllItems();

    List<T> getItemsById(String id);

    T getItemById(String id);

    T create(Class<T> clazz);
}
