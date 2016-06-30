package com.grability.rappi.view.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.grability.rappi.Commons.Common;
import com.grability.rappi.R;

/**
 * Created by jorgesanmartin on 3/31/16.
 */
public class STTNumberPicker extends NumberPicker {

    //Vars
    private int mItemsColor = Color.BLACK;
    private int mItemsSize = Common.DEFAULT_SIZE;
    private int mItemsFont;

    public STTNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {

        if (attributeSet != null) {
            TypedArray attributes = null;
            try {
                attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.STTNumberPicker);
                setItemsColor(attributes.getColor(R.styleable.STTNumberPicker_itemsTitleColor, Color.BLACK));
                setItemsSize(attributes.getDimensionPixelSize(R.styleable.STTNumberPicker_itemsTitleSize, Common.DEFAULT_SIZE));
            } catch (Exception ex) {
                Log.e("", ex.getMessage(), ex);
            } finally {
                if (attributes != null) {
                    attributes.recycle();
                }
            }
        }
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    private void updateView(View view) {
        if (view instanceof EditText) {
            ((EditText) view).setTextSize(25);
            ((EditText) view).setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }

    // Public methods

    public int getItemsColor() {
        return mItemsColor;
    }

    public void setItemsColor(int mItemColor) {
        this.mItemsColor = mItemColor;
    }

    public int getItemsSize() {
        return mItemsSize;
    }

    public void setItemsSize(int mItemSize) {
        this.mItemsSize = mItemSize;
        invalidate();
        requestLayout();
    }

    public int getItemsFont() {
        return mItemsFont;
    }

    public void setItemsFont(int mItemFont) {
        this.mItemsFont = mItemFont;
    }
}
