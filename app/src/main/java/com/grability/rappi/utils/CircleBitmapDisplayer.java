package com.grability.rappi.utils;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

/**
 * Created by jorgesanmartin on 11/27/15.
 */
public class CircleBitmapDisplayer extends RoundedBitmapDisplayer {

    private final boolean alphaAnimation;

    public CircleBitmapDisplayer() {
        super(0);
        this.alphaAnimation = false;
    }

    public CircleBitmapDisplayer(boolean alphaAnimation) {
        super(0);
        this.alphaAnimation = alphaAnimation;
    }

    @Override
    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        if (!(imageAware instanceof ImageViewAware)) {
            throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
        }

        RoundedBitmapDrawable rounddrawable =
                RoundedBitmapDrawableFactory.create(imageAware.getWrappedView().getResources(), bitmap);
        rounddrawable.setCircular(true);

        if (alphaAnimation) {
            imageAware.getWrappedView().setAlpha(0.0f);
        }

        imageAware.setImageDrawable(rounddrawable);

        if (alphaAnimation) {
            imageAware.getWrappedView().animate().alpha(1.0f).start();
        }
    }
}
