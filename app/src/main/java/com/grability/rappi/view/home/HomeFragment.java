package com.grability.rappi.view.home;

import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.presenter.home.HomePresenter;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomeFragment extends MVPFragment<HomePresenter, HomePresenter.View> implements HomePresenter.View, HomeScreen.Listener {

    private HomeScreen listPhrasesScreen;

    @Override
    protected View getRootView() {
        listPhrasesScreen = new HomeScreen(getActivity());
        listPhrasesScreen.setListener(this);
        return listPhrasesScreen;
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

    }

    // HomePresenter.View


    // HomeScreen.Listener

    @Override
    public void onSettingsPressed() {
        getPresenter().settingsPressed();
    }

    @Override
    public void ageSelected(int age) {
        getPresenter().startPressed(age);
    }
}
