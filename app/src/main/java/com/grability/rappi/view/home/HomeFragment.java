package com.grability.rappi.view.home;

import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.presenter.home.HomePresenter;
import com.grability.rappi.view.base.RappiActivity;

import java.util.List;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomeFragment extends MVPFragment<HomePresenter, HomePresenter.View> implements HomePresenter.View, HomeScreen.Listener {

    private HomeScreen homeScreen;
    private List<AppItem> items;

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
    public void setItems(List<AppItem> list) {
        this.items = list;
        homeScreen.addItems(list);
    }

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

    @Override
    public List<AppItem> getItems() {
        return items;
    }

    // HomeScreen.Listener

    @Override
    public void onItemClickPressed(int position) {
        getPresenter().onItemPressed(position);
    }

    @Override
    public void onCategoriesPressed() {
        homeScreen.setCategory(true, getItems());
    }

    @Override
    public void onApplicationPressed() {
        homeScreen.setCategory(false, getItems());
    }
}
