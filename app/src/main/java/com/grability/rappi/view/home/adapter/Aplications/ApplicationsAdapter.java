package com.grability.rappi.view.home.adapter.Aplications;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cronosgroup.core.view.BaseAdapter;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.view.home.adapter.Aplications.viewholder.CategoryViewHolder;

import java.util.List;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public class ApplicationsAdapter extends BaseAdapter<CategoryViewHolder, AppItem> {

    private boolean categories;

    @Override
    public RecyclerView.ViewHolder getHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lay_item, parent, false);
        return new CategoryViewHolder(view).setClickListener(getClickListener());
    }

    @Override
    public void configItem(CategoryViewHolder holder, int position, boolean isLastItem) {
        holder.configureItem(getItem(position));
    }

    @Override
    public List<AppItem> filterBy(String query) {
        return null;
    }

    public boolean isCategories() {
        return categories;
    }

    public void setCategories(boolean categories) {
        this.categories = categories;
    }
}
