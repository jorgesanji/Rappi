package com.grability.rappi.view.home.adapter.Categories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cronosgroup.core.view.BaseAdapter;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.database.model.AppCategory;
import com.grability.rappi.view.home.adapter.Categories.viewholder.CategoriesViewHolder;

import java.util.List;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public class CategoriesAdapter extends BaseAdapter<CategoriesViewHolder, AppCategory> {

    @Override
    public RecyclerView.ViewHolder getHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lay_item, parent, false);
        return new CategoriesViewHolder(view).setClickListener(getClickListener());
    }

    @Override
    public void configItem(CategoriesViewHolder holder, int position, boolean isLastItem) {
        holder.configureItem(getItem(position));
    }

    @Override
    public List<AppCategory> filterBy(String query) {
        return null;
    }

}
