package com.grability.rappi.model.dataacess.database.managers;

import com.grability.rappi.model.dataacess.database.model.AppCategory;
import com.grability.rappi.model.dataacess.database.repository.AppCategoryRepository;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by jorgesanmartin on 7/4/16.
 */
public class CategoryManager {

    private AppCategoryRepository appCategoryRepository;

    public CategoryManager() {
        this.appCategoryRepository = new AppCategoryRepository();
    }

    public List<AppCategory> getAllItemOrderByName() {
        RealmResults<AppCategory> items = (RealmResults<AppCategory>) appCategoryRepository.getAllItems();
        items.sort("label");
        return items;
    }

}
