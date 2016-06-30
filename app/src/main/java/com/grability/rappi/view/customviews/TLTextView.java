package com.grability.rappi.view.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.grability.rappi.R;
import com.grability.rappi.utils.TypeFaceUtils;

/**
 * Created by jorgesanmartin on 21/10/15.
 */
public class TLTextView extends TextView {

    private static final String TAG = TLTextView.class.toString();
    private static final int DEFAULT_FONT = 0;

    // Vars
    private int fontName;
    private boolean itemSelected = false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TLTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public TLTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public TLTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TLTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray attributes = null;
            try {
                attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.TLTextView);
                setFontName(attributes.getInt(R.styleable.TLTextView_fontName, DEFAULT_FONT));
            } catch (Exception ex) {
                Log.e(TAG, ex.getMessage(), ex);
            } finally {
                if (attributes != null) {
                    attributes.recycle();
                }
            }
        }
    }

    // Public methods

    public int getFontName() {
        return fontName;
    }

    public void setFontName(int fontName) {
        this.fontName = fontName;
        this.setTypeface(TypeFaceUtils.getFontWithFlag(getContext(), fontName));
    }
}
