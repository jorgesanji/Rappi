package com.grability.rappi.presenter.base;

import android.content.Intent;

import com.cronosgroup.core.managers.PermissionsManager;
import com.cronosgroup.core.presenter.Presenter;
import com.cronosgroup.core.view.BaseActivity;
import com.grability.rappi.view.AppStatusMessageManager;

/**
 * Created by jorgesanmartin on 21/4/16.
 */
public abstract class RappiPresenter<V extends Presenter.View> implements Presenter<V> {

    protected V view;
    protected AppStatusMessageManager mStatusManager;

    public RappiPresenter() {
    }

    @Override
    public void attachView(V view) {
        this.view = view;
        this.mStatusManager = new AppStatusMessageManager(view);
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public V getView() {
        return view;
    }

    public AppStatusMessageManager getStatusView() {
        return mStatusManager;
    }

    public PermissionsManager getPermissionManager() {
        return ((BaseActivity) getView().getActivity()).getPermissionsManager();
    }

}
