package com.grability.rappi.presenter.home;

import android.app.Activity;
import android.os.Bundle;

import com.cronosgroup.core.presenter.Presenter;
import com.cronosgroup.core.rest.Callback;
import com.cronosgroup.core.rest.RestError;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.model.business.logic.RappiUseCases;
import com.grability.rappi.model.dataacess.rest.model.RestEntry;
import com.grability.rappi.model.dataacess.rest.model.RestResponse;
import com.grability.rappi.presenter.base.RappiPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomePresenter extends RappiPresenter<HomePresenter.View> {

    private final Actions listener;

    public HomePresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
        void setItems(List<RestEntry> list);
    }

    public interface Actions {
        void onSettingsPressed(Activity activity, Bundle bundle);

        void onStartPressed(Activity activity, Bundle bundle);
    }

    //Public methods

    public void settingsPressed() {
        listener.onSettingsPressed(getView().getActivity(), null);
    }

    public void startPressed(int age) {
        Bundle bundle = new Bundle();
        bundle.putInt(Common.KEY_AGE, age);
        listener.onStartPressed(getView().getActivity(), bundle);
    }

    public void getItems() {
        RappiUseCases.getItems(new Callback<RestResponse, RestError>() {
            @Override
            public void onResponse(RestResponse response) {
                getView().setItems(new ArrayList<RestEntry>(Arrays.asList(response.getFeed().getEntry())));
            }

            @Override
            public void onErrorResponse(RestError error) {

            }
        }, getView().getActivity());

    }

}
