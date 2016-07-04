package com.grability.rappi.view.detail.adapter.page;

import android.os.Bundle;
import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.presenter.detail.ItemDetailPresenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ItemDetailFragment extends MVPFragment<ItemDetailPresenter, ItemDetailPresenter.View> implements ItemDetailPresenter.View, ItemDetailScreen.Listener {

    private ItemDetailScreen itemDetailScreen;
    private AppItem appItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getRootView() {
        itemDetailScreen = new ItemDetailScreen(getActivity());
        itemDetailScreen.setListener(this);
        return itemDetailScreen;
    }

    @Override
    protected ItemDetailPresenter createPresenter() {
        return new ItemDetailPresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected ItemDetailPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidAppear() {
        getPresenter().getDetailItemWithId(getArguments().getString(Common.ITEM_ID));
    }

    // ItemDetailPresenser.View

    @Override
    public void setAppItem(AppItem appItem) {
        this.appItem = appItem;
        itemDetailScreen.setAppName(appItem.getName());
        itemDetailScreen.setAppDescription(appItem.getDescription());
        itemDetailScreen.setAppUrl(appItem.getThumbImage());
        itemDetailScreen.setAppCategoryName(appItem.getCategory().getLabel());
    }

    // ItemDestailScreen.Listener

}
