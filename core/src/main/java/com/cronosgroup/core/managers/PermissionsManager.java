package com.cronosgroup.core.managers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.cronosgroup.core.view.BaseActivity;

import java.util.List;

/**
 * Created by jorgesanmartin on 1/20/16.
 */
public class PermissionsManager {

    public interface IOAppPermission {
        void permission(Permission permission, boolean enable);
    }

    public static final int CODE_REQUEST_LOCATION = 255;
    public static final int CODE_REQUEST_CAMERA = 245;
    public static final int CODE_REQUEST_SMS = 235;
    public static final int CODE_REQUEST_GALLERY = 225;
    public static final int CODE_REQUEST_READ_CONTACTS = 200;
    public static final int CODE_REQUEST_ALL = 100;

    public static final String[] CONTACTOS_PERMISSIONS = new String[]{Manifest.permission.READ_CONTACTS};
    public static final String[] CAMERA_PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final String[] GALLERY_PERMISSIONS = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    public static final String[] LOCATION_PERMISSIONS = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final String[] SMS_PERMISSIONS = new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS};

    public static final String[] ALL_PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS};


    public enum Permission {

        CONTACTOS(CONTACTOS_PERMISSIONS, CODE_REQUEST_READ_CONTACTS),
        CAMERA(CAMERA_PERMISSIONS, CODE_REQUEST_CAMERA),
        GALLERY(GALLERY_PERMISSIONS, CODE_REQUEST_GALLERY),
        LOCATION(LOCATION_PERMISSIONS, CODE_REQUEST_LOCATION),
        SMS(SMS_PERMISSIONS, CODE_REQUEST_SMS),
        ALL(ALL_PERMISSIONS, CODE_REQUEST_ALL),
        UNKNOW(new String[]{}, -1);

        private final String[] permissions;
        private final int code;

        private Permission(String[] permissions, int code) {
            this.permissions = permissions;
            this.code = code;
        }

        public String[] getPermissions() {
            return permissions;
        }

        public int getCode() {
            return code;
        }

        public static Permission getPermissionFromCode(int code) {
            switch (code) {
                case CODE_REQUEST_READ_CONTACTS:
                    return CONTACTOS;
                case CODE_REQUEST_LOCATION:
                    return LOCATION;
                case CODE_REQUEST_CAMERA:
                    return CAMERA;
                case CODE_REQUEST_SMS:
                    return SMS;
                case CODE_REQUEST_GALLERY:
                    return GALLERY;
                case CODE_REQUEST_ALL:
                    return ALL;
                default:
                    return UNKNOW;
            }
        }
    }

    private BaseActivity activity;
    private Context context;

    public PermissionsManager(BaseActivity activity) {
        super();
        this.activity = activity;
    }

    public PermissionsManager(Context context) {
        super();
        this.context = context;
    }

    private void requestPermissions(String[] permission, int code, IOAppPermission permissionListener) {
        if (getActivity() != null) {
            if (permissionListener != null){
                getActivity().setPermissionListener(permissionListener);
            }
            ActivityCompat.requestPermissions(getActivity(),
                    permission, code);
        }
    }

    protected boolean checkPermissions(String[] param) {
        for (String permission : param) {
            if (!(ContextCompat.checkSelfPermission(getContext(),
                    permission) == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    protected void requestPermissions(Permission permission, IOAppPermission permissionListener) {
        requestPermissions(permission.getPermissions(), permission.getCode(), permissionListener);
    }

    //Public Methods

    public void requestPermissions(List<Permission> permissionsList) {
        for (Permission permission : permissionsList) {
            requestPermissions(permission, null);
        }
    }

    public void requestContactPermission(IOAppPermission permissionListener) {
        requestPermissions(Permission.CONTACTOS, permissionListener);
    }

    public void requestContactPermission() {
        requestPermissions(Permission.CONTACTOS, null);
    }

    public void requestCameraPermission(IOAppPermission permissionListener) {
        requestPermissions(Permission.CAMERA, permissionListener);
    }

    public void requestCameraPermission() {
        requestPermissions(Permission.CAMERA, null);
    }

    public void requestGalleryPermission() {
        requestPermissions(Permission.GALLERY, null);
    }

    public void requestGalleryPermission(IOAppPermission permissionListener) {
        requestPermissions(Permission.GALLERY, permissionListener);
    }

    public void requestLocationPermission(IOAppPermission permissionListener) {
        requestPermissions(Permission.LOCATION, permissionListener);
    }

    public void requestLocationPermission() {
        requestPermissions(Permission.LOCATION, null);
    }

    public void requestSmsPermission(IOAppPermission permissionListener) {
        requestPermissions(Permission.SMS, permissionListener);
    }

    public void requestSmsPermission() {
        requestPermissions(Permission.SMS, null);
    }

    public boolean checkContactPermissions() {
        return checkPermissions(Permission.CONTACTOS.getPermissions());
    }

    public boolean checkLocationPermissions() {
        return checkPermissions(Permission.LOCATION.getPermissions());
    }

    public boolean checkCameraPermissions() {
        return checkPermissions(Permission.CAMERA.getPermissions());
    }

    public boolean checkCameraCropPermissions() {
        return checkPermissions(Permission.CAMERA.getPermissions());
    }

    public boolean checkGalleryPermissions() {
        return checkPermissions(Permission.GALLERY.getPermissions());
    }

    public boolean checkSmsPermissions() {
        return checkPermissions(Permission.SMS.getPermissions());
    }

    public Context getContext() {
        return context != null ? context : getActivity();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }

    public BaseActivity getActivity() {
        return activity;
    }
}

