package com.grability.rappi.view.home.adapter.viewholder;

import android.view.View;

import com.cronosgroup.core.view.recyclerview.BaseViewHolder;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.rest.model.RestEntry;
import com.grability.rappi.view.customviews.RPImageView;
import com.grability.rappi.view.customviews.RPTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 7/1/16.
 */
public class ItemViewHolder extends BaseViewHolder<RestEntry> {

    @BindView(R.id.appImage)
    RPImageView appImage;

    @BindView(R.id.appName)
    RPTextView name;

    public ItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void configureItem(RestEntry item) {
        super.configureItem(item);
        name.setText(item.name.label);
        if (item.image.length != 0) {
            appImage.setImageFromUrl(item.image[0].label);
        }
    }
}
