package com.grability.rappi.view;

import com.cronosgroup.core.presenter.Presenter;
import com.cronosgroup.core.presenter.StatusMessageManager;
import com.grability.rappi.R;


/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class AppStatusMessageManager extends StatusMessageManager {

    @Override
    public int getErrorColor() {
        return android.R.color.holo_red_dark;
    }

    @Override
    public int getSucessColor() {
        return android.R.color.holo_green_light;
    }

    @Override
    public int getWarningColor() {
        return R.color.colorPrimaryDark;
    }

    public AppStatusMessageManager(Presenter.View view) {
        super(view);
    }

    // Errors

    public void showNetworkError() {
        showError(R.string.network_error);
    }

}
