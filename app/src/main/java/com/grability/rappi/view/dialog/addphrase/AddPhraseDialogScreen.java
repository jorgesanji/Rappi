package com.grability.rappi.view.dialog.addphrase;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.grability.rappi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jorgesanmartin on 10/26/15.
 */
public class AddPhraseDialogScreen extends LinearLayout {

    /**
     * listeners of the StudyDialog's screen.
     */
    public interface Listener {
        void onAddPressed();
    }

    // Vars
    private Listener listener;

    // Views
    @BindView(R.id.phraseText)
    EditText mPhraseText;


    /**
     * @param context
     */
    public AddPhraseDialogScreen(Context context, Listener listener) {
        super(context);
        this.listener = listener;
        init();
    }

    /**
     * @param context
     */
    public AddPhraseDialogScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public AddPhraseDialogScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public AddPhraseDialogScreen(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public AddPhraseDialogScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_add_phrase, this);
        ButterKnife.bind(this);
        mPhraseText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mPhraseText, InputMethodManager.SHOW_IMPLICIT);
    }

    @OnClick(R.id.addButton)
    protected void addPressed() {
        listener.onAddPressed();
    }

    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public String getPrhase() {
        return mPhraseText.getText().toString();
    }

}
