package com.grability.rappi.view.home.adapter.Categories.viewholder;

import android.view.View;

import com.cronosgroup.core.view.recyclerview.BaseViewHolder;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.database.model.AppCategory;
import com.grability.rappi.view.customviews.RPImageView;
import com.grability.rappi.view.customviews.RPTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public class CategoriesViewHolder extends BaseViewHolder<AppCategory> {

    @BindView(R.id.appImage)
    RPImageView image;

    @BindView(R.id.appName)
    RPTextView name;

    public CategoriesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void configureItem(AppCategory item) {
        super.configureItem(item);
        image.setVisibility(View.GONE);
        name.setText(item.getLabel());
    }
}
