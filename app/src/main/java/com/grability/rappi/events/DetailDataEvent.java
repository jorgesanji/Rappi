package com.grability.rappi.events;

import com.grability.rappi.model.dataacess.database.model.AppItem;

import java.util.List;

/**
 * Created by jorgesanmartin on 3/7/16.
 */
public class DetailDataEvent {
    private final List<AppItem> items;
    private final int position;

    public DetailDataEvent(List<AppItem> items, int position) {
        this.items = items;
        this.position = position;
    }

    public List<AppItem> getItems() {
        return items;
    }

    public int getPosition() {
        return position;
    }
}
