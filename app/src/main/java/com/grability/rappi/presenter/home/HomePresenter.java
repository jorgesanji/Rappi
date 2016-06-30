package com.grability.rappi.presenter.home;

import android.app.Activity;
import android.os.Bundle;

import com.cronosgroup.core.presenter.Presenter;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.presenter.base.RappiPresenter;


/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomePresenter extends RappiPresenter<HomePresenter.View> {

    private final Actions listener;

    public HomePresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
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

}
