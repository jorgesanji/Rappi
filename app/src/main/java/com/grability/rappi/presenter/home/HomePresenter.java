package com.grability.rappi.presenter.home;

import android.app.Activity;
import android.os.Bundle;

import com.cronosgroup.core.presenter.Presenter;
import com.cronosgroup.core.rest.Callback;
import com.cronosgroup.core.rest.RestError;
import com.grability.rappi.events.BusProvider;
import com.grability.rappi.events.DetailDataEvent;
import com.grability.rappi.model.business.logic.RappiUseCases;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.presenter.base.RappiPresenter;
import com.grability.rappi.utils.NetworkConnection;

import java.util.List;

import io.realm.Realm;


/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomePresenter extends RappiPresenter<HomePresenter.View> {

    private final Actions listener;

    public HomePresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
        void setItems(List<AppItem> list);

        List<AppItem> getItems();
    }

    public interface Actions {
        void onItemPressed(Activity activity, Bundle bundle);
    }

    //Public methods

    public void getItems() {
        final Realm realm = Realm.getDefaultInstance();
        if (!NetworkConnection.isConnected(getView().getContext())) {
            getStatusView().showNetworkError();
            getView().setItems(realm.where(AppItem.class).findAll());
        } else {

            getView().showLoading();

            RappiUseCases.getItems(new Callback<List<AppItem>, RestError>() {
                @Override
                public void onResponse(List<AppItem> items) {
                    getView().setItems(items);
                    getView().hideLoading();
                }

                @Override
                public void onErrorResponse(RestError error) {
                    getStatusView().showNetworkError();
                    getView().setItems(realm.where(AppItem.class).findAll());
                    getView().hideLoading();
                }
            }, getView().getActivity());
        }
    }

    public void onItemPressed(int position) {
        DetailDataEvent detailDataEvent = new DetailDataEvent(getView().getItems(), position);
        listener.onItemPressed(getView().getActivity(), null);
        BusProvider.getInstance().postWithDelay(detailDataEvent);
    }
}
