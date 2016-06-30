package com.cronosgroup.core.view.crop;

import android.support.v4.app.Fragment;
import android.view.View;

import com.cronosgroup.core.managers.PermissionsManager;
import com.cronosgroup.core.view.ToolBarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgesanmartin on 10/30/15.
 */
public class CropActivity extends ToolBarActivity {

    @Override
    public Fragment getFragment() {
        Fragment fragment = Fragment.instantiate(this, CropFragment.class.getName());
        fragment.setArguments(getIntent().getExtras());
        return fragment;
    }

    @Override
    public boolean hasToolbar() {
        return false;
    }

    @Override
    public StyleToolBar getActivityStyle() {
        return null;
    }

    @Override
    public int getActivityIconBack() {
        return 0;
    }

    @Override
    public int getActivityIcon() {
        return 0;
    }

    @Override
    public int getActivityTitle() {
        return 0;
    }

    @Override
    public boolean canLaunchPermission() {
        return false;
    }

    @Override
    public List<PermissionsManager.Permission> getRequestPermission() {
        return new ArrayList<>();
    }

    @Override
    public View getView() {
        return null;
    }
}
