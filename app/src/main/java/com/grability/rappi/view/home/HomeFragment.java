package com.grability.rappi.view.home;

import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.model.dataacess.rest.model.RestEntry;
import com.grability.rappi.presenter.home.HomePresenter;

import java.util.List;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomeFragment extends MVPFragment<HomePresenter, HomePresenter.View> implements HomePresenter.View, HomeScreen.Listener {

    private HomeScreen homeScreen;

    @Override
    protected View getRootView() {
        homeScreen = new HomeScreen(getActivity());
        homeScreen.setListener(this);
        return homeScreen;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected HomePresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidAppear() {
        getPresenter().getItems();
    }

    // HomePresenter.View

    @Override
    public void setItems(List<RestEntry> list) {
        homeScreen.addItems(list);
    }

    // HomeScreen.Listener

    @Override
    public void onItemClickPressed(int position) {

    }
}
