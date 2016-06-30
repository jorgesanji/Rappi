package com.grability.rappi.view.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cronosgroup.core.managers.PermissionsManager;
import com.cronosgroup.core.view.BaseActivity;
import com.grability.rappi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class RappiActivity extends BaseActivity {

    public enum StyleToolBar {
        WHITESTYLE(1),
        DEFAULTSTYLE(2);

        private int style;

        private StyleToolBar(int style) {
            this.style = style;
        }

        public int getStyle() {
            return style;
        }
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.progressBar)
    View mLoader;

    private View view;

    private Fragment currentFragment;

    public abstract boolean hasToolbar();

    public abstract StyleToolBar getActivityStyle();

    public abstract int getActivityIconBack();

    public abstract Fragment getFragment();

    @Override
    public View getView() {
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.lay_activity_base, null);
        }
        return view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        ButterKnife.bind(this);
        initToolbar();
        initFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    private void initToolbar() {

        if (!hasToolbar()) {
            mToolbar.setVisibility(View.GONE);
        } else {
            if (mToolbar != null) {
                if (getActivityStyle() == StyleToolBar.WHITESTYLE) {
                    configToolBarWhiteMode();
                } else if (getActivityStyle() == StyleToolBar.DEFAULTSTYLE) {
                    configDefaultMode();
                }

                setSupportActionBar(mToolbar);
                if (getActivityIconBack() != 0) {
                    getSupportActionBar().setHomeAsUpIndicator(getActivityIconBack());
                }
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(getString(R.string.app_name));
            }
        }
    }

    private void initFragment() {
        this.currentFragment = getFragment();
        if (currentFragment != null) {
            currentFragment.setArguments(getIntent().getExtras());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, currentFragment);
            transaction.commit();
        }
    }

    private void configToolbar(int backgroundColor, String title, int textColor, Drawable logo) {
        mToolbar.setBackgroundColor(backgroundColor);
        mToolbar.setTitle(title);
        mToolbar.setTitleTextColor(textColor);
        mToolbar.setLogo(logo);
    }

    protected void configToolBarWhiteMode() {
        configToolbar(getResources().getColor(R.color.white), "", getResources().getColor(R.color.black), null);
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_loader);
    }

    protected void configDefaultMode() {
        configToolbar(getResources().getColor(android.R.color.transparent), "", getResources().getColor(R.color.black), null);
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_loader);
    }

    //Public methods

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public void showLoading() {
        mLoader.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        mLoader.setVisibility(View.GONE);
    }

    @Override
    public int getStatusColor() {
        return getResources().getColor(R.color.colorPrimary);
    }

    @Override
    public boolean canLaunchPermission() {
        return false;
    }

    @Override
    public List<PermissionsManager.Permission> getRequestPermission() {
        return null;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
