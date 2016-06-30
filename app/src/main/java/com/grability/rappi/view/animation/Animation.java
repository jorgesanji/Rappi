package com.grability.rappi.view.animation;

import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

/**
 * Created by jorgesanmartin on 3/31/16.
 */
public class Animation {

    public static AnimationSet getScaleAndAphaAnimation() {

        ScaleAnimation grow = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f,
                android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        grow.setDuration(100);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(100);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.addAnimation(grow);
        animationSet.addAnimation(alphaAnimation);

        return animationSet;
    }
}
