package com.grability.rappi.view.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;

import com.grability.rappi.R;
import com.grability.rappi.view.animation.Animation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomeScreen extends RelativeLayout {

    public interface Listener {
        void onSettingsPressed();

        void ageSelected(int age);
    }

    // Vars
    private Listener listener;

    // Views
    @BindView(R.id.ageSelector)
    NumberPicker mAgeSelector;

    @BindView(R.id.containerAgeSelector)
    View mView;

    /**
     * @param context
     */
    public HomeScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public HomeScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public HomeScreen(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public HomeScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_home, this);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        mAgeSelector.setMinValue(8);
        mAgeSelector.setMaxValue(12);
    }

    // Actions

    @OnClick(R.id.containerDialog)
    protected void disableTouchDialog() {
    }

    @OnClick(R.id.startButton)
    protected void startPresssed() {
        mView.setVisibility(VISIBLE);
        mView.startAnimation(Animation.getScaleAndAphaAnimation());
    }

    @OnClick(R.id.settingsButton)
    protected void settingsPresssed() {
        listener.onSettingsPressed();
    }

    @OnClick(R.id.ageSelected)
    protected void selectedPressed() {
        listener.ageSelected(mAgeSelector.getValue());
        mView.setVisibility(GONE);
    }

    @OnClick(R.id.containerAgeSelector)
    protected void containerPressed() {
        mView.setVisibility(GONE);
    }

    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

}
