package com.grability.rappi.view.listphrases;

import com.cronosgroup.core.managers.PermissionsManager;
import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.R;
import com.grability.rappi.view.base.RappiActivity;

import java.util.ArrayList;
import java.util.List;

public class ListPhrasesActivity extends RappiActivity {

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
    public MVPFragment getFragment() {
        return new ListPhrasesFragment();
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
