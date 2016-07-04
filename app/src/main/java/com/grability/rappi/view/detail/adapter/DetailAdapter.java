package com.grability.rappi.view.detail.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.grability.rappi.Commons.Common;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.view.detail.adapter.page.ItemDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgesanmartin on 3/7/16.
 */
public class DetailAdapter extends FragmentStatePagerAdapter {

    private List<AppItem> items = new ArrayList<>();

    public DetailAdapter(FragmentManager fm, List<AppItem> items) {
        super(fm);
        this.items = items;
    }

    @Override
    public Fragment getItem(int position) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Common.ITEM_ID, items.get(position).getId());
        itemDetailFragment.setArguments(bundle);
        return itemDetailFragment;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public List<AppItem> getItems() {
        return items;
    }

    public void setItems(List<AppItem> items) {
        this.items = items;
    }

}
