package com.grability.rappi.view.home.adapter.Aplications.viewholder;

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
public class CategoryViewHolder extends BaseViewHolder<AppItem> {

    @BindView(R.id.appImage)
    RPImageView image;

    @BindView(R.id.appName)
    RPTextView name;

    @BindView(R.id.appPrice)
    RPTextView price;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void configureItem(AppItem item) {
        super.configureItem(item);
        name.setText(item.getName());
        price.setText(item.getPrice().substring(0, 3) + item.getCurrency());
        if (item.getThumbImage() != null) {
            image.setImageFromUrl(item.getThumbImage());
        }
    }

}
