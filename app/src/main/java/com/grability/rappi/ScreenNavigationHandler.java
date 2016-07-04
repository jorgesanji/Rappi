package com.grability.rappi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.grability.rappi.presenter.detail.DetailPresenter;
import com.grability.rappi.presenter.detail.ItemDetailPresenter;
import com.grability.rappi.presenter.home.HomePresenter;
import com.grability.rappi.presenter.splash.SplashPresenter;
import com.grability.rappi.view.detail.AppDetailActivity;
import com.grability.rappi.view.home.HomeActivity;


/**
 * Created by jorgesanmartin on 2/26/16.
 */
public final class ScreenNavigationHandler implements HomePresenter.Actions, SplashPresenter.Actions,
        DetailPresenter.Actions, ItemDetailPresenter.Actions {

    public static final String URL = "url";

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

        if (bundle != null) {
            openIntent.putExtras(bundle);
        }

        return openIntent;
    }

    // ------------------------ CREATION INTENTS -----------------------------------

    private static Intent home(@NonNull Activity context, Bundle bundle) {
        return newTask(context, HomeActivity.class, bundle, true);
    }

    private static Intent detail(@NonNull Activity context, Bundle bundle) {
        return newTask(context, AppDetailActivity.class, bundle);
    }

    private static Intent navigation(@NonNull Activity context, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString(URL)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    // ------------------------ ACTIONS DEFINITION -----------------------------------

    //--- SPLASH --

    @Override
    public void onLaunchHome(Activity activity, Bundle bundle) {
        startActivity(activity, home(activity, bundle));
    }

    //--- HOME --

    @Override
    public void onItemPressed(Activity activity, Bundle bundle) {
        startActivity(activity, detail(activity, bundle));
    }

    //--- ITEM --

    @Override
    public void onLinkPressed(Activity activity, Bundle bundle) {
        startActivity(activity, navigation(activity, bundle));
    }
}
