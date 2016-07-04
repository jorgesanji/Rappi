package com.grability.rappi.model.dataacess.database.repository.domain.categories;

import com.grability.rappi.model.dataacess.database.model.AppCategory;
import com.grability.rappi.model.dataacess.database.repository.domain.base.IORepository;

import java.util.List;

/**
 * Created by jorgesanmartin on 7/4/16.
 */
public interface CategoryRepository<T> extends IORepository<AppCategory> {

    List<AppCategory> getCategories();
}
