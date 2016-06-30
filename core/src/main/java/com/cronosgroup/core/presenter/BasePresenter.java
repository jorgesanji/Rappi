package com.cronosgroup.core.presenter;

import android.content.Intent;

/**
 * Implements the common functionalities of the presenters.
 */
public abstract class BasePresenter<V extends Presenter.View> implements Presenter<V> {

    V view;

    public BasePresenter() {
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
    public void attachView(V view) {
        this.view = view;
    }

    @Override
    public V getView() {
        return view;
    }

}
