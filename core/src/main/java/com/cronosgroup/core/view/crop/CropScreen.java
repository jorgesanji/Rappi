package com.cronosgroup.core.view.crop;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.cronosgroup.core.R;
import com.isseiaoki.simplecropview.CropImageView;


/**
 * Crop view.
 */
public class CropScreen extends RelativeLayout {

    /**
     * listeners of the home's screen.
     */

    public interface Listener {
        void onCropImage(Bitmap original, Bitmap croppedBitmap);
    }

    // Vars
    private Listener listener;
    private Bitmap mBitmap;

    // Views
    private CropImageView mCropImageView;
    private FloatingActionButton mCropButton;

    /**
     * @param context
     */
    public CropScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public CropScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public CropScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CropScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_crop, this);
        mCropImageView = (CropImageView)findViewById(R.id.cropImageView);
        mCropButton = (FloatingActionButton)findViewById(R.id.cropButton);
        mCropButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCropImage(mBitmap, mCropImageView.getCroppedBitmap());
                }
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    // Public methods

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        mCropImageView.setImageBitmap(mBitmap);
    }
}