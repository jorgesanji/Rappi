package com.grability.rappi.view.home;

import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.R;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.presenter.home.HomePresenter;
import com.grability.rappi.view.base.RappiActivity;

import java.util.List;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomeFragment extends MVPFragment<HomePresenter, HomePresenter.View> implements HomePresenter.View, HomeScreen.Listener {

    private HomeScreen homeScreen;
    private List items;

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
        ((RappiActivity) getActivity()).getSupportActionBar().setTitle(R.string.home_categories);
        getPresenter().getItems();
    }

    // HomePresenter.View

    @Override
    public void setItems(List list) {
        this.items = list;
        homeScreen.setCategory(false, list);
    }

    @Override
    public void setCategories(List list) {
        this.items = list;
        homeScreen.setCategory(true, list);
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
    public List getItems() {
        return items;
    }

    // HomeScreen.Listener

    @Override
    public void onItemClickPressed(int position) {
        getPresenter().onItemPressed(position);
    }

    @Override
    public void onCategoriesPressed() {
        ((RappiActivity) getActivity()).getSupportActionBar().setTitle(R.string.home_categories);
        getPresenter().getCategories();
    }

    @Override
    public void onApplicationPressed() {
        ((RappiActivity) getActivity()).getSupportActionBar().setTitle(R.string.home_applications);
        getPresenter().getAplications();
    }
}
