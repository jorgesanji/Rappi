package com.cronosgroup.core.view.crop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.cronosgroup.core.managers.ImagePickerManager;
import com.cronosgroup.core.presenter.CropPresenter;
import com.cronosgroup.core.utils.BitmapUtils;
import com.cronosgroup.core.utils.FileUtils;
import com.cronosgroup.core.view.MVPFragment;

import java.io.File;


/**
 * Sucess Validation Fragment
 */
public class CropFragment extends MVPFragment<CropPresenter, CropPresenter.View>
        implements CropPresenter.View, CropScreen.Listener {

    private CropScreen cropScreen;
    private String originalImagePath;

    //region **************  Fragment **************

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        originalImagePath = getArguments().getString(ImagePickerManager.IMAGE_TO_CROP);
    }

    @Override
    protected View getRootView() {
        cropScreen = new CropScreen(getActivity());
        cropScreen.setListener(this);
        return cropScreen;
    }

    //endregion

    //region **************  MVPFragment **************

    @Override
    protected CropPresenter createPresenter() {
        return new CropPresenter(null);
    }

    @Override
    protected CropPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidAppear() {
        Bitmap bitmap = BitmapUtils.decodeFromFile(originalImagePath);
        cropScreen.setBitmap(bitmap);
    }

    //region **************  CropScreen.Listener **************

    @Override
    public void onCropImage(Bitmap original, Bitmap cropped) {
        Pair<File, Bitmap> pair = FileUtils.saveBitmapInFile(getContext(), cropped, ImagePickerManager.IMAGE_CROPPED);
        File fileTmp = pair.first;
        String cropPathImage = fileTmp.getAbsolutePath();

        Intent output = new Intent();
        output.putExtra(ImagePickerManager.IMAGE_ORIGINAL, originalImagePath);
        output.putExtra(ImagePickerManager.IMAGE_CROPPED, cropPathImage);
        getActivity().setResult(Activity.RESULT_OK, output);
        getActivity().finish();
    }
}
