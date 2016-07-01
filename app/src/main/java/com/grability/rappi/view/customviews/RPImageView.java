package com.grability.rappi.view.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.grability.rappi.R;
import com.grability.rappi.utils.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by jorgesanmartin on 11/10/15.
 */
public class RPImageView extends ImageView {

    public static final String TAG = RPImageView.class.getName();

    private boolean rounded;
    private Drawable placeHolderErrorImage;
    private Drawable placerHolderEmptyUri;
    private ImageLoadingListener listener;

    /**
     * @param context
     */
    public RPImageView(Context context) {
        super(context);
        init(null);
    }

    /**
     * @param context
     * @param attrs
     */
    public RPImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RPImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RPImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }


    protected void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray attributes = null;
            try {
                attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RPImageView);
                setRounded(attributes.getBoolean(R.styleable.RPImageView_imageRounded, false));
                setPlaceHolderErrorImage(attributes.getDrawable(R.styleable.RPImageView_imageError));
                setPlacerHolderEmptyUri(attributes.getDrawable(R.styleable.RPImageView_imageEmptyUri));
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage(), ex);
            } finally {
                if (attributes != null) {
                    attributes.recycle();
                }
            }
        }
    }

    private void loadImageFromUrl(String url, ImageLoadingListener listener) {
        cancelLoad();

        if (url != null) {
            this.listener = listener;
            DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
            options.cacheOnDisk(true);
            if (getPlacerHolderEmptyUri() != null) {
                options.showImageForEmptyUri(getPlacerHolderEmptyUri());
            }

            if (getPlaceHolderErrorImage() != null) {
                options.showImageOnFail(getPlaceHolderErrorImage());
            }

            if (isRounded()) {
                options.displayer(new CircleBitmapDisplayer(false));
            }

            ImageLoader.getInstance().displayImage(url, this, (options != null) ? options.build() : null, listener);
        } else {
            setImageDrawable(null);
        }
    }

    //Publics methods

    public ImageLoadingListener getListener() {
        return listener;
    }

    public void setListener(ImageLoadingListener listener) {
        this.listener = listener;
    }

    public Drawable getPlaceHolderErrorImage() {
        return placeHolderErrorImage;
    }

    public void setPlaceHolderErrorImage(Drawable placeHolderErrorImage) {
        this.placeHolderErrorImage = placeHolderErrorImage;
    }

    public Drawable getPlacerHolderEmptyUri() {
        return placerHolderEmptyUri;
    }

    public void setPlacerHolderEmptyUri(Drawable placerHolderEmptyUri) {
        this.placerHolderEmptyUri = placerHolderEmptyUri;
    }

    public void setImageFromUrl(String url) {
        loadImageFromUrl(url, null);
    }

    public void setImageFromUrl(String url, ImageLoadingListener listener) {
        loadImageFromUrl(url, listener);
    }

    public boolean isRounded() {
        return rounded;
    }

    public void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    public void cancelLoad() {
        ImageLoader.getInstance().cancelDisplayTask(this);
        this.setImageBitmap(null);
    }
}
