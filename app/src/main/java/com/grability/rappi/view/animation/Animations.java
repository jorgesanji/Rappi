package com.grability.rappi.view.animation;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by jorgesanmartin on 3/7/16.
 */
public class Animations {

    public interface Listener {
        void onFinishAnimation();
    }

    public static void revealFromTop(final View view, final int color) {
        reveal(view, color, null);
    }

    public static void revealFromTop(final View view, final int color, final Listener listener) {
        reveal(view, color, listener);
    }

    public static void reveal(final View view, final int color, final Listener listener) {

        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int cx = (view.getLeft() + view.getRight()) / 2;
                    int cy = view.getBottom();
                    int finalRadius = Math.max(view.getWidth(), view.getHeight());

                    Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
                    view.setBackgroundColor(view.getContext().getResources().getColor(color));
                    anim.start();
                    anim.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (listener != null) {
                                listener.onFinishAnimation();
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });

                } else {
                    if (listener != null) {
                        listener.onFinishAnimation();
                    }
                }
            }
        });

    }
}