package com.grability.rappi.view.home.adapter.viewholder;

import android.view.View;

import com.cronosgroup.core.view.recyclerview.BaseViewHolder;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.view.customviews.RPImageView;
import com.grability.rappi.view.customviews.RPTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public class ItemViewHolder extends BaseViewHolder<AppItem> {

    private boolean isCategory;

    @BindView(R.id.appImage)
    RPImageView appImage;

    @BindView(R.id.appName)
    RPTextView name;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void configureItem(AppItem item) {
        super.configureItem(item);
        if (!isCategory()) {
            name.setText(item.getName());
            if (item.getThumbImage() != null) {
                appImage.setImageFromUrl(item.getThumbImage());
            }
        } else {
            appImage.setVisibility(View.GONE);
            name.setText(item.getCategory().getLabel());
        }
    }

    public boolean isCategory() {
        return isCategory;
    }

    public void setCategory(boolean category) {
        isCategory = category;
    }
}
