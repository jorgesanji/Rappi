package com.grability.rappi.view.animation;

import android.animation.Animator;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.grability.rappi.view.customviews.RPMenuButton;

/**
 * Created by jorgesanmartin on 11/17/15.
 */
public class ScrollHandler extends RecyclerView.OnScrollListener {

    private static final int TIME_TO_APPEAR_BUTTONS = 150;
    private static final int DISTANCE_TO_HIDE = 300;

    public ScrollHandler(RPMenuButton bottomView, View topView, View middleView) {
        this.bottomView = bottomView;
        this.topView = topView;
        this.middleView = middleView;
    }

    private RPMenuButton bottomView;
    private View topView;
    private View middleView;
    private int mState;
    private Handler handler = new Handler();
    private boolean animationShowInProgress;
    private boolean animationHideInProgress;

    private Runnable showRunnable = new Runnable() {
        @Override
        public void run() {

            AnimationViewListener animListener = new AnimationViewListener() {
                @Override
                public void startAnimation() {
                    animationShowInProgress = true;
                }
            };

            bottomView.animate().translationY(0).setListener(animListener).start();

            if (topView != null) {
                topView.animate().translationY(0).setListener(animListener).start();
            }

            if (middleView != null) {
                middleView.animate().translationY(0).setListener(animListener).start();
            }
        }
    };

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        mState = newState;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy < 0) {
            if (!animationShowInProgress) {
                animationHideInProgress = false;
                handler.postDelayed(showRunnable, TIME_TO_APPEAR_BUTTONS);
            }
        } else {
            if (mState == RecyclerView.SCROLL_STATE_DRAGGING) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) bottomView.getLayoutParams();
                handler.removeCallbacks(showRunnable);
                if (!animationHideInProgress) {
                    animationShowInProgress = false;
                    AnimationViewListener animListener = new AnimationViewListener() {
                        @Override
                        public void startAnimation() {
                            animationHideInProgress = true;
                        }
                    };

                    bottomView.collapseMenu();
                    bottomView.animate().translationY(params.height + DISTANCE_TO_HIDE).setListener(animListener).start();

                    if (topView != null) {
                        topView.animate().translationY(params.height - DISTANCE_TO_HIDE).setListener(animListener).start();
                    }

                    if (middleView != null) {
                        middleView.animate().translationY(params.height - DISTANCE_TO_HIDE).setListener(animListener).start();
                    }
                }
            }
        }
    }

    public View getBottomView() {
        return bottomView;
    }

    public void setBottomView(RPMenuButton bottomView) {
        this.bottomView = bottomView;
    }

    public View getTopView() {
        return topView;
    }

    public void setTopView(View topView) {
        this.topView = topView;
    }

    public View getMiddleView() {
        return middleView;
    }

    public void setMiddleView(View middleView) {
        this.middleView = middleView;
    }

    public void show() {
        animationHideInProgress = false;
        showRunnable.run();
    }

    /**
     * Private abastract class which implements animation Listener
     */
    private abstract static class AnimationViewListener implements Animator.AnimatorListener {

        public abstract void startAnimation();

        @Override
        public void onAnimationStart(Animator animation) {
            startAnimation();
        }

        @Override
        public void onAnimationEnd(Animator animation) {
        }

        @Override
        public void onAnimationCancel(Animator animation) {
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
        }
    }
}
