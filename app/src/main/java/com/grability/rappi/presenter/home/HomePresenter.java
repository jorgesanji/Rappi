package com.grability.rappi.presenter.home;

import android.app.Activity;
import android.os.Bundle;

import com.cronosgroup.core.presenter.Presenter;
import com.cronosgroup.core.rest.Callback;
import com.cronosgroup.core.rest.RestError;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.events.BusProvider;
import com.grability.rappi.events.DetailDataEvent;
import com.grability.rappi.model.business.logic.RappiUseCases;
import com.grability.rappi.model.dataacess.database.managers.CategoryManager;
import com.grability.rappi.model.dataacess.database.managers.ItemsManager;
import com.grability.rappi.model.dataacess.database.model.AppCategory;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.presenter.base.RappiPresenter;
import com.grability.rappi.utils.NetworkConnection;

import java.util.List;


/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomePresenter extends RappiPresenter<HomePresenter.View> {

    private final Actions listener;
    private ItemsManager itemsManager = new ItemsManager();
    private CategoryManager categoryManager = new CategoryManager();

    public HomePresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
        void setItems(List list);

        void setCategories(List list);

        List<AppItem> getItems();
    }

    public interface Actions {
        void onItemPressed(Activity activity, Bundle bundle);
    }

    //Public methods

    public void getItems() {
        if (!NetworkConnection.isConnected(getView().getContext())) {
            getStatusView().showNetworkError();
            getView().setItems(categoryManager.getAllItemOrderByName());
        } else {

            getView().showLoading();

            RappiUseCases.getItems(new Callback<List<AppCategory>, RestError>() {
                @Override
                public void onResponse(List<AppCategory> items) {
                    getView().setCategories(items);
                    getView().hideLoading();
                }

                @Override
                public void onErrorResponse(RestError error) {
                    getStatusView().showNetworkError();
                    getView().setItems(categoryManager.getAllItemOrderByName());
                    getView().hideLoading();
                }
            }, getView().getActivity());
        }
    }

    public void onItemPressed(int position) {
        List items = getView().getItems();
        Bundle bundle = null;
        if (items.get(position) instanceof AppCategory) {
            AppCategory appCategory = ((AppCategory) items.get(position));
            items = itemsManager.getItemsById(appCategory.getId());
            position = 0;
            bundle = new Bundle();
            bundle.putString(Common.ITEM_NAME, appCategory.getLabel());
        }

        listener.onItemPressed(getView().getActivity(), bundle);

        DetailDataEvent detailDataEvent = new DetailDataEvent(items, position);
        BusProvider.getInstance().postWithDelay(detailDataEvent);
    }

    public void getCategories() {
        getView().setCategories(categoryManager.getAllItemOrderByName());
    }

    public void getAplications() {
        getView().setItems(itemsManager.getAllItemOrderByName());
    }
}
