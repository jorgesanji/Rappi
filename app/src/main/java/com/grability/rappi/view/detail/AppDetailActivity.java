package com.grability.rappi.view.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.cronosgroup.core.managers.PermissionsManager;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.R;
import com.grability.rappi.view.base.RappiActivity;

import java.util.ArrayList;
import java.util.List;

public class AppDetailActivity extends RappiActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() != null && getIntent().getStringExtra(Common.ITEM_NAME) != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra(Common.ITEM_NAME));
        }
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }

    @Override
    public StyleToolBar getActivityStyle() {
        return StyleToolBar.DEFAULTSTYLE;
    }

    @Override
    public int getActivityIconBack() {
        return 0;
    }

    @Override
    public Fragment getFragment() {
        AppDetailFragment appDetailFragment = new AppDetailFragment();
        appDetailFragment.setArguments(getIntent().getExtras());
        return appDetailFragment;
    }

    @Override
    public boolean canLaunchPermission() {
        return true;
    }

    @Override
    public int getStatusColor() {
        return R.color.colorPrimary;
    }

    @Override
    public List<PermissionsManager.Permission> getRequestPermission() {
        List<PermissionsManager.Permission> permissionList = new ArrayList<>();
        return permissionList;
    }
}
