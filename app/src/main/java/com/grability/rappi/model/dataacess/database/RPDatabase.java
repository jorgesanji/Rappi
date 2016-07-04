package com.grability.rappi.model.dataacess.database;

import com.cronosgroup.core.view.application.BaseApplication;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public class RPDatabase {

    public static final String DATABASE_NAME = "RappiDatabase";

    public static void initDataBase(BaseApplication aplication) {
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(aplication.getApplicationContext())
                .name(DATABASE_NAME)
                .build());
    }

}
