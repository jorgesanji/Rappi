package com.cronosgroup.core.presenter;

import android.content.Context;

/**
 * Created by jorgesanmartin on 11/19/15.
 */
public abstract class StatusMessageManager {

    public abstract int getErrorColor();

    public abstract int getSucessColor();

    public abstract int getWarningColor();

    private final Presenter.View view;

    public StatusMessageManager(Presenter.View view) {
        this.view = view;
    }

    public Context getContext() {
        return getView().getContext();
    }

    public Presenter.View getView() {
        return view;
    }

    public void showError(int message) {
        Context context = getContext();
        if (context != null) {
            getView().showError(context.getString(message), getErrorColor());
        }
    }

    public void showSuccess(int message) {
        Context context = getContext();
        if (context != null) {
            getView().showInfo(context.getString(message), getSucessColor());
        }
    }

    public void showWarning(int message) {
        Context context = getContext();
        if (context != null) {
            getView().showInfo(context.getString(message), getWarningColor());
        }
    }

    public void showError(String message) {
        if (getContext() != null) {
            getView().showError(message, getErrorColor());
        }
    }

    public void showSuccess(String message) {
        if (getContext() != null) {
            getView().showInfo(message, getSucessColor());
        }
    }

    public void showWarning(String message) {
        if (getContext() != null) {
            getView().showInfo(message, getWarningColor());
        }
    }
}
