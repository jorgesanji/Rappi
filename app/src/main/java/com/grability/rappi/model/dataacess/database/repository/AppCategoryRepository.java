package com.grability.rappi.model.dataacess.database.repository;

import com.grability.rappi.model.dataacess.database.model.AppCategory;
import com.grability.rappi.model.dataacess.database.repository.domain.categories.CategoryRepository;

import java.util.List;

/**
 * Created by jorgesanmartin on 7/4/16.
 */
public class AppCategoryRepository implements CategoryRepository<AppCategory> {

    @Override
    public List<AppCategory> getAllItems() {
        return realm.where(AppCategory.class).findAll();
    }

    @Override
    public List<AppCategory> getItemsById(String id) {
        return realm.where(AppCategory.class).equalTo("id", id).findAll();
    }

    @Override
    public AppCategory getItemById(String id) {
        return null;
    }

    @Override
    public AppCategory create(Class<AppCategory> clazz) {
        return null;
    }

    @Override
    public List<AppCategory> getCategories() {
        return realm.distinct(AppCategory.class, "id");
    }
}
