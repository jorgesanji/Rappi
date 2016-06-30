package com.grability.rappi.view.settings;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.grability.rappi.Commons.Common;
import com.grability.rappi.R;
import com.grability.rappi.utils.DurationUtils;
import com.grability.rappi.utils.UserPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SettingsScreen extends RelativeLayout {


    public interface Listener {
        void addPressed();
    }

    // Vars
    Listener listener;

    // Views
    @BindView(R.id.seekView)
    SeekBar mSeekBar;

    @BindView(R.id.timeSelection)
    TextView mTimeSelection;

    /**
     * @param context
     */
    public SettingsScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public SettingsScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SettingsScreen(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public SettingsScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_settings, this);
        ButterKnife.bind(this);
        initUI();
        initListeners();
    }

    private void initUI() {
        mSeekBar.setMax(Common.DEFAULT_MAX_MILLIS);
        int duration = DurationUtils.getCurrentDuration(getContext());
        mSeekBar.setProgress(duration);
        setProgressDuration(duration);
    }

    private void initListeners() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setProgressDuration(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setProgressDuration(int progress) {
        float time = (float) progress / Common.MILLIS_SECONDS;
        mTimeSelection.setText(String.format("%.2f %s", time, getResources().getString(R.string.settings_minutes)));
        UserPreferences.saveDuration(getContext(), time);
    }

    // Actions

    @OnClick(R.id.addPhrase)
    protected void onAddPhrasePressed() {
        listener.addPressed();
    }

    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

}
