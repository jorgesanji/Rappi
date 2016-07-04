package com.grability.rappi.view.splash;

import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.presenter.splash.SplashPresenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SplashFragment extends MVPFragment<SplashPresenter, SplashPresenter.View> implements SplashPresenter.View, SplashScreen.Listener {

    private SplashScreen splashScreen;
    private Runnable launchHome = new Runnable() {
        @Override
        public void run() {
            getPresenter().onLaunchHome();
        }
    };
    private Handler mHandler = new Handler(Looper.myLooper());

    @Override
    protected View getRootView() {
        splashScreen = new SplashScreen(getActivity());
        splashScreen.setListener(this);
        return splashScreen;
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected SplashPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidAppear() {
        mHandler.postDelayed(launchHome, Common.SPLASH_DURATION);
    }

    // SplashPresenter.View

    // SplashScreen.Listener

}
