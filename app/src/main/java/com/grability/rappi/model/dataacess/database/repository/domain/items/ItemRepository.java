package com.grability.rappi.model.dataacess.database.repository.domain.items;

import com.grability.rappi.model.dataacess.database.repository.domain.base.IORepository;

/**
 * Created by jorgesanmartin on 7/4/16.
 */
public interface ItemRepository<T, R> extends IORepository {

    void save(T item);

    void deleteAll();

    T create(Class<T> clazz, R rest);
}
