package com.grability.rappi.view.detail;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.grability.rappi.Commons.Common;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.view.detail.adapter.DetailAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class AppDetailScreen extends RelativeLayout {

    public interface Listener {

    }

    // Vars
    private Listener listener;
    private DetailAdapter mAdapter;

    // Views
    @BindView(R.id.pager)
    ViewPager mPager;

    @BindView(R.id.backgroundFadeIn)
    View mBackgroundIn;

    @BindView(R.id.backgroundFadeOut)
    View mBackgroundOut;

    /**
     * @param context
     */
    public AppDetailScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public AppDetailScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public AppDetailScreen(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public AppDetailScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_app_detail, this);
        ButterKnife.bind(this);
        initPager();
        initUI();
    }

    private void initUI() {
        mBackgroundIn.setAlpha(0);
    }

    private void initPager() {
        mPager.setClipToPadding(false);
        mPager.setPadding(80, 0, 80, 0);
        mPager.setPageMargin(0);
    }

    // Actions


    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void initView(FragmentManager fragmentManager, List<AppItem> appItemList, int position) {
        mAdapter = new DetailAdapter(fragmentManager, appItemList);
        mPager.setAdapter(mAdapter);
        mPager.setCurrentItem(position);
        mPager.invalidate();
        mPager.requestLayout();
    }

    public void animBackground() {
        mBackgroundIn.animate().alpha(1).setDuration(Common.TIME_TO_ANIMATION).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mBackgroundOut.animate().alpha(0).setDuration(Common.TIME_TO_ANIMATION).setStartDelay(Common.DELAY_TO_ANIMATION).setInterpolator(new AccelerateInterpolator()).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).start();
    }

    public View getAnimableView() {
        return mBackgroundOut;
    }

}
