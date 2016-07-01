package com.cronosgroup.core.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cronosgroup.core.R;
import com.cronosgroup.core.view.application.BaseApplication;

/**
 * Created by jorgesanmartin on 16/10/15.
 */

public abstract class ToolBarActivity extends BaseActivity<BaseApplication> {

    public enum StyleToolBar {
        WHITESTYLE(1),
        LINKERSTYLE(2),
        TINKERSTYLE(3),
        CLEARSTYLE(4),
        LINKERCARDSTYLE(5),
        TINKERCARDSTYLE(6),
        HOMESTYLE(7);

        private int style;

        private StyleToolBar(int style) {
            this.style = style;
        }

        public int getStyle() {
            return style;
        }
    }

    public abstract Fragment getFragment();

    public abstract boolean hasToolbar();

    public abstract StyleToolBar getActivityStyle();

    public abstract int getActivityIconBack();

    public abstract int getActivityIcon();

    public abstract int getActivityTitle();

    private Fragment currentFragment;
    Toolbar mToolbar;
    View mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        mLoader = findViewById(R.id.progressBar);
        initToolbar();
        initFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initFragment() {
        this.currentFragment = getFragment();
        if (currentFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, currentFragment);
            transaction.commit();
        }
    }

    private void initToolbar() {

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (!hasToolbar()) {
            mToolbar.setVisibility(View.GONE);
        } else {
            if (mToolbar != null) {
                if (getActivityStyle() == StyleToolBar.LINKERSTYLE) {
                    configLinkerDefaultMode();
                } else if (getActivityStyle() == StyleToolBar.WHITESTYLE) {
                    configToolBarWhiteMode();
                } else if (getActivityStyle() == StyleToolBar.CLEARSTYLE) {
                    configClearMode();
                } else if (getActivityStyle() == StyleToolBar.LINKERCARDSTYLE) {
                    configLinkerCardMode();
                } else if (getActivityStyle() == StyleToolBar.TINKERCARDSTYLE) {
                    configTinkerCardMode();
                } else if (getActivityStyle() == StyleToolBar.HOMESTYLE) {
                    configHomeStyle();
                } else {
                    configTinkerDefaultMode();
                }

                setSupportActionBar(mToolbar);

                if (getActivityIconBack() != 0) {
                    getSupportActionBar().setHomeAsUpIndicator(getActivityIconBack());
                }

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                if (getActivityIcon() != 0) {
                    getSupportActionBar().setIcon(getResources().getDrawable(getActivityIcon()));
                }

                if (getActivityTitle() != 0) {
                    getSupportActionBar().setTitle(getString(getActivityTitle()));
                }
            }
        }
    }

    private void configToolbar(int backgroundColor, String title, int textColor, Drawable logo) {
        mToolbar.setBackgroundColor(backgroundColor);
        mToolbar.setTitle(title);
        mToolbar.setTitleTextColor(textColor);
        mToolbar.setLogo(logo);
    }

    protected void configToolBarWhiteMode() {
        configToolbar(getResources().getColor(R.color.gray), "", getResources().getColor(R.color.black), null);
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_tinker_loader);
    }

    protected void configLinkerDefaultMode() {
        configToolbar(getResources().getColor(R.color.white), "", getResources().getColor(R.color.white), getResources().getDrawable(R.mipmap.ic_ti_02));
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_linker_loader);
    }

    protected void configTinkerDefaultMode() {
        configToolbar(getResources().getColor(R.color.white), "", getResources().getColor(R.color.white), getResources().getDrawable(R.mipmap.ic_ti_02));
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_tinker_loader);
    }

    protected void configClearMode() {
        configToolbar(getResources().getColor(android.R.color.transparent), "", getResources().getColor(R.color.black), null);
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_tinker_loader);
    }

    protected void configLinkerCardMode() {
        configToolbar(getResources().getColor(R.color.white), "", getResources().getColor(R.color.white), getResources().getDrawable(R.mipmap.ic_ti_02));
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_tinker_loader);
    }

    protected void configTinkerCardMode() {
        configToolbar(getResources().getColor(R.color.white), "", getResources().getColor(R.color.white), getResources().getDrawable(R.mipmap.ic_ti_02));
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_tinker_loader);
    }

    protected void configHomeStyle() {
        configToolbar(getResources().getColor(R.color.gray), "", getResources().getColor(R.color.white), getResources().getDrawable(R.mipmap.ic_ti_02));
        mLoader.findViewById(R.id.backgroundColor).setBackgroundResource(R.drawable.background_tinker_loader);
    }


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
        return getResources().getColor(R.color.gray);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
