package com.grability.rappi.view.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.grability.rappi.R;
import com.grability.rappi.utils.TypeFaceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 3/8/16.
 */
public class RPMenuItem extends LinearLayout {

    //Vars
    public static final int DEFAULT_FONT = 0;
    public static final int DEFAULT_SIZE = 12;

    //Views
    @BindView(R.id.itemButton)
    FloatingActionButton mItemButton;

    @BindView(R.id.itemTitle)
    RPTextView mItemTitle;

    /**
     * @param context
     */
    public RPMenuItem(Context context) {
        this(context, null, 0);
    }

    /**
     * @param context
     * @param attrs
     */
    public RPMenuItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RPMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RPMenuItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        inflate(getContext(), R.layout.lay_item_menu, this);
        ButterKnife.bind(this);

        if (attributeSet != null) {
            TypedArray attributes = null;
            try {
                attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RPMenuItem);
                setMenuItemTitle(attributes.getString(R.styleable.RPMenuItem_menuItemTitle));
                setMenuItemImage(attributes.getDrawable(R.styleable.RPMenuItem_menuItemImageResource));
                setMenuItemBackgroundTintColor(attributes.getColor(R.styleable.RPMenuItem_menuItemBackgroundTintColor, Color.BLACK));
                setMenuItemFontName(attributes.getInt(R.styleable.RPMenuItem_menuItemTitleFont, DEFAULT_FONT));
                setMenuItemTitleColor(attributes.getColor(R.styleable.RPMenuItem_menuItemTitleColor, Color.BLACK));
                setMenutItemTitlesize(attributes.getDimensionPixelSize(R.styleable.RPMenuItem_menuItemTitleSize, DEFAULT_SIZE));
            } catch (Exception ex) {
                Log.e(RPMenuItem.class.getName(), ex.getMessage(), ex);
            } finally {
                if (attributes != null) {
                    attributes.recycle();
                }
            }
        }
    }

    //Public methods

    public void setMenuItemTitle(String title) {
        mItemTitle.setText(title);
    }

    public void setMenuItemImage(Drawable drawable) {
        mItemButton.setImageDrawable(drawable);
    }

    public void setMenuItemBackgroundTintColor(int tintColor) {
        mItemButton.setBackgroundTintList(ColorStateList.valueOf(tintColor));
    }

    public void setMenuItemFontName(int fontName) {
        mItemTitle.setTypeface(TypeFaceUtils.getFontWithFlag(getContext(), fontName));
    }

    public void setMenuItemTitleColor(int titleColor) {
        mItemTitle.setTextColor(titleColor);
    }

    public void setMenutItemTitlesize(float titlesize) {
        mItemTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titlesize);
    }
}
