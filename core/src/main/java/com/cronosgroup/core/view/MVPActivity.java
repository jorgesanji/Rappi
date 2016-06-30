package com.cronosgroup.core.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;

import com.cronosgroup.core.presenter.Presenter;

/**
 * Common functionalities for activities.
 * Handles life cycle of presenters.
 */
public abstract class MVPActivity<P extends Presenter<V>, V extends Presenter.View> extends BaseActivity implements Presenter.View {

    private P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = createPresenter();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (presenter != null) {
            presenter.attachView(getPresenterView());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.resume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (presenter != null) {
            presenter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    @NonNull
    public Context getContext() {
        return this;
    }

    @Override
    public MVPFragment getFragment() {
        return null;
    }

    @Override
    @NonNull
    public Activity getActivity() {
        return this;
    }

    protected P getPresenter() {
        return this.presenter;
    }

    protected abstract P createPresenter();

    protected abstract V getPresenterView();

    /**
     * Shows an info toast with title and a message in a new line.
     * It is recommended to override it.
     */
    @Override
    public void showInfo(@NonNull String message, int color) {
        makeBar(message, (color == 0) ? Color.GREEN : color);
    }

    /**
     * Shows a warning toast with title and a message in a new line.
     * It is recommended to override it.
     */
    @Override
    public void showWarning(@NonNull String message, int color) {
        makeBar(message, (color == 0) ? Color.YELLOW : color);
    }

    /**
     * Shows an error toast with title and a message in a new line.
     * It is recommended to override it.
     */
    @Override
    public void showError(@NonNull String message, int color) {
        makeBar(message, (color == 0) ? Color.RED : color);
    }

    private void makeBar(String text, int color) {
        Snackbar snackbar = Snackbar.make(getView(), text, Snackbar.LENGTH_LONG);
        ViewGroup group = (ViewGroup) snackbar.getView();
        group.setBackgroundColor(getResources().getColor(color));
        snackbar.show();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }


}
