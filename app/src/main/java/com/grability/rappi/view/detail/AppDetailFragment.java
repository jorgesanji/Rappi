package com.grability.rappi.view.detail;

import android.os.Bundle;
import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.R;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.events.BusProvider;
import com.grability.rappi.events.DetailDataEvent;
import com.grability.rappi.presenter.detail.DetailPresenter;
import com.grability.rappi.view.animation.Animations;
import com.grability.rappi.view.base.RappiActivity;
import com.squareup.otto.Subscribe;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class AppDetailFragment extends MVPFragment<DetailPresenter, DetailPresenter.View> implements DetailPresenter.View, AppDetailScreen.Listener {

    private AppDetailScreen appDetailScreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    protected View getRootView() {
        appDetailScreen = new AppDetailScreen(getActivity());
        appDetailScreen.setListener(this);
        return appDetailScreen;
    }

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected DetailPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidAppear() {
        showLoading();
        Animations.revealFromTop(appDetailScreen.getAnimableView(), R.color.colorPrimary, new Animations.Listener() {
            @Override
            public void onFinishAnimation() {
                appDetailScreen.animBackground();
            }
        });
    }

    // DetailPresenter.View

    @Override
    public void showLoading() {
        if (getActivity() != null) {
            ((RappiActivity) getActivity()).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (getActivity() != null) {
            ((RappiActivity) getActivity()).hideLoading();
        }
    }

    // DetailScreen.Listener

    // Bus
    @Subscribe
    public void detailEvent(DetailDataEvent event) {
        appDetailScreen.initView(getFragmentManager(), event.getItems(), event.getPosition());
        hideLoading();
    }

    @Override
    public void onDestroy() {
        BusProvider.getInstance().unRegister(this);
        super.onDestroy();
    }
}
