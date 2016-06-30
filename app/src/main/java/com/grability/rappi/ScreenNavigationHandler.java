package com.grability.rappi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.grability.rappi.presenter.home.HomePresenter;
import com.grability.rappi.presenter.listphrases.ListPhrasesPresenter;
import com.grability.rappi.presenter.recognizer.RecognizerPresenter;
import com.grability.rappi.presenter.settings.SettingsPresenter;
import com.grability.rappi.presenter.splash.SplashPresenter;
import com.grability.rappi.view.home.HomeActivity;
import com.grability.rappi.view.listphrases.ListPhrasesActivity;
import com.grability.rappi.view.recognizer.RecognizerActivity;
import com.grability.rappi.view.settings.SettingsActivity;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public final class ScreenNavigationHandler implements ListPhrasesPresenter.Actions, RecognizerPresenter.Actions, HomePresenter.Actions, SettingsPresenter.Actions, SplashPresenter.Actions {

    //Instance
    private static ScreenNavigationHandler instance = null;

    private ScreenNavigationHandler() {
    }

    /**
     * @return the instance of ScreenNavigationHandler
     */
    public static ScreenNavigationHandler getInstance() {
        if (instance == null) {
            instance = new ScreenNavigationHandler();
        }
        return instance;
    }

    // ---------------------------- LAUNCH INTENT -------------------------------

    private static void startActivity(Activity context, Intent intent) {
        context.startActivity(intent);
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private static void startActivityWithResult(Activity context, Intent intent, int code) {
        context.startActivityForResult(intent, code);
        context.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private static Intent newTask(@NonNull Activity context, @NonNull Class clazz, Bundle bundle) {
        return newTask(context, clazz, bundle, false);
    }

    private static Intent newTask(@NonNull Activity context, @NonNull Class clazz, Bundle bundle, boolean clearTop) {
        Intent openIntent = new Intent(context, clazz);
        if (clearTop) {
            openIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return openIntent;
    }

    // ------------------------ CREATION INTENTS -----------------------------------

    private static Intent home(@NonNull Activity context, Bundle bundle) {
        return newTask(context, HomeActivity.class, bundle, true);
    }

    private static Intent list(@NonNull Activity context, Bundle bundle) {
        return newTask(context, ListPhrasesActivity.class, bundle);
    }

    private static Intent phrase(@NonNull Activity context, Bundle bundle) {
        return newTask(context, RecognizerActivity.class, bundle);
    }

    private static Intent settings(@NonNull Activity context, Bundle bundle) {
        return newTask(context, SettingsActivity.class, bundle);
    }

    // ------------------------ ACTIONS DEFINITION -----------------------------------

    //---SPLASH --

    @Override
    public void onLaunchHome(Activity activity, Bundle bundle) {
        startActivity(activity, home(activity, bundle));
    }

    //---HOME --

    @Override
    public void onSettingsPressed(Activity activity, Bundle bundle) {
        startActivity(activity, settings(activity, bundle));
    }

    @Override
    public void onStartPressed(Activity activity, Bundle bundle) {
        startActivity(activity, list(activity, bundle));
    }

    //--RECOGINIZER  --

    @Override
    public void onSelectedPhrase(@NonNull Activity activity, Bundle bundle) {
        startActivity(activity, phrase(activity, bundle));
    }
}
