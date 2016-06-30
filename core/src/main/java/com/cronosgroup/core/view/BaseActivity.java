package com.cronosgroup.core.view;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cronosgroup.core.managers.AnalyticsManager;
import com.cronosgroup.core.managers.PermissionsManager;
import com.cronosgroup.core.view.application.BaseApplication;

import java.util.List;

/**
 * Created by jorgesanmartin on 12/1/15.
 */
public abstract class BaseActivity<A extends BaseApplication> extends AppCompatActivity {

    public static final long TIME_TO_WAIT = 500;

    private A application;
    private PermissionsManager permissionsManager;
    private boolean active;
    private PermissionsManager.IOAppPermission permissionListener;
    private Handler mHandler = new Handler(Looper.myLooper());
    private Runnable mRemoveActivity = new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };

    public abstract boolean canLaunchPermission();

    public abstract List<PermissionsManager.Permission> getRequestPermission();

    public abstract int getStatusColor();

    public abstract View getView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (A) getApplication();
        initPermissionsManager();
        initStatusBar();
        pushScreenToAnalytics();
    }

    protected void pushScreenToAnalytics() {
        AnalyticsManager.getInstance().trackScreen(this.getClass().getSimpleName());
    }

    private void initStatusBar() {
        setStatusColor(getStatusColor());
    }

    private void initPermissionsManager() {
        permissionsManager = new PermissionsManager(this);
        if (canLaunchPermission() && getRequestPermission() != null) {
            permissionsManager.requestPermissions(getRequestPermission());
        }
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRemoveActivity);
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        PermissionsManager.Permission permission = PermissionsManager.Permission.getPermissionFromCode(requestCode);
        boolean permissionAccepted = (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED);

        if (application instanceof PermissionsManager.IOAppPermission) {
            ((PermissionsManager.IOAppPermission) application).permission(permission, permissionAccepted);
        }

        if (permissionListener != null) {
            permissionListener.permission(permission, permissionAccepted);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setStatusColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    public AppCompatDialogFragment addDialogFragment(Class clazz) {
        return addDialogFragment(clazz, null);
    }

    public AppCompatDialogFragment addDialogFragment(Class clazz, Bundle bundle) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        AppCompatDialogFragment fragment = (AppCompatDialogFragment) AppCompatDialogFragment.instantiate(this, clazz.getName());
        fragment.setArguments(bundle);
        fragment.show(fragmentTransaction, clazz.toString());

        return fragment;
    }

    public void removeActivityDelay() {
        mHandler.removeCallbacks(mRemoveActivity);
        mHandler.postDelayed(mRemoveActivity, TIME_TO_WAIT);
    }

    public void forceRequestPermission() {
        permissionsManager.requestPermissions(getRequestPermission());
    }

    public PermissionsManager.IOAppPermission getPermissionListener() {
        return permissionListener;
    }

    public void setPermissionListener(PermissionsManager.IOAppPermission permissionListener) {
        this.permissionListener = permissionListener;
    }

    public PermissionsManager getPermissionsManager() {
        return permissionsManager;
    }
}
