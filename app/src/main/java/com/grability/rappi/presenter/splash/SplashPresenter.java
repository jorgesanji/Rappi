package com.grability.rappi.presenter.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cronosgroup.core.presenter.Presenter;
import com.grability.rappi.presenter.base.RappiPresenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SplashPresenter extends RappiPresenter<SplashPresenter.View> {

    private final Actions listener;

    public SplashPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
    }

    public interface Actions {
        void onLaunchHome(Activity activity, Bundle bundle);
    }

    // BasePresenter
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    //Public methods

    public void onLaunchHome() {
        listener.onLaunchHome(getView().getActivity(), null);
    }
}
