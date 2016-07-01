package com.grability.rappi.view.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cronosgroup.core.view.BaseAdapter;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.rest.model.RestEntry;
import com.grability.rappi.view.home.adapter.viewholder.ItemViewHolder;

import java.util.List;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public class ListAdapter extends BaseAdapter<ItemViewHolder, RestEntry> {

    @Override
    public RecyclerView.ViewHolder getHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lay_item, parent, false);
        return new ItemViewHolder(view).setClickListener(getClickListener());
    }

    @Override
    public void configItem(ItemViewHolder holder, int position, boolean isLastItem) {
        holder.configureItem(getItem(position));
    }

    @Override
    public List<RestEntry> filterBy(String query) {
        return null;
    }
}
