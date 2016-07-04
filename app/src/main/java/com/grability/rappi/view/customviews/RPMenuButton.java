package com.grability.rappi.view.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.grability.rappi.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.PropertyValuesHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 3/8/16.
 */
public class RPMenuButton extends LinearLayout {

    public interface IOMenuButtonState {
        void collapsed();

        void expanded();
    }

    private static final String TRANSLATION_Y = "translationY";
    private static final String ALPHA = "alpha";

    //Vars
    private boolean expanded = true;
    private List<Float> mPositions = new ArrayList<>();
    private int mTintcolor;
    private Drawable mIcon;
    private Drawable mIconExpanded;
    private IOMenuButtonState listener;

    //Views
    FloatingActionButton mMenuButton;

    /**
     * @param context
     */
    public RPMenuButton(Context context) {
        this(context, null, 0);
    }

    /**
     * @param context
     * @param attrs
     */
    public RPMenuButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RPMenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public RPMenuButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        setOrientation(VERTICAL);
        setGravity(Gravity.RIGHT);
        inflate(getContext(), R.layout.lay_menu_button, this);
        ButterKnife.bind(this);
        initUI();

        if (attributeSet != null) {
            TypedArray attributes = null;
            try {
                attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RPMenuButton);
                setMenuButtonImage(attributes.getDrawable(R.styleable.RPMenuButton_menuButtonImageResource));
                setMenuButtonExpandedImage(attributes.getDrawable(R.styleable.RPMenuButton_menuButtonStateExpandedImageResource));
                setMenuButtonBackgroundTintColor(attributes.getColor(R.styleable.RPMenuButton_menuButtonBackgroundTintColor, Color.BLACK));
            } catch (Exception ex) {
                Log.e(RPMenuItem.class.getName(), ex.getMessage(), ex);
            } finally {
                if (attributes != null) {
                    attributes.recycle();
                }
            }
        }

    }

    private void initUI() {
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < 16) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                collapseMenu();
                expanded = false;
            }
        });
    }

    private void configMainButton() {
        mMenuButton = new FloatingActionButton(getContext());
        mMenuButton.setBackgroundTintList(ColorStateList.valueOf(mTintcolor));
        mMenuButton.setImageDrawable(mIcon);
        addView(mMenuButton);
        mMenuButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
                animateMenu();
            }
        });
    }

    private void animateFab() {
        Drawable drawable = mMenuButton.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        mMenuButton.animate().rotation(expanded ? 90 : -120).start();
    }

    private void animateMenu() {
        mMenuButton.setImageDrawable(expanded ? mIconExpanded : mIcon);

        boolean isPositionsFilled = mPositions.size() > 0;
        List<Animator> list = new ArrayList<>();
        int indexButtonIn = 0;
        for (int index = 0; index < getChildCount(); index++) {
            View view = getChildAt(index);
            if (view != mMenuButton && view instanceof RPMenuItem) {
                view.setEnabled(expanded);
                float posTransitionY = mMenuButton.getY() - view.getY();
                if (isPositionsFilled) {
                    posTransitionY = mPositions.get(indexButtonIn).floatValue();
                } else {
                    mPositions.add(Float.valueOf(posTransitionY));
                }
                list.add(!expanded ? createCollapseAnimator(view, posTransitionY) : createExpandAnimator(view, posTransitionY));
                indexButtonIn++;
            }
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(list);
        animatorSet.start();
        animateFab();

        if (isPositionsFilled) {
            mPositions.clear();
        }

        if (getListener() != null) {
            if (expanded) {
                getListener().expanded();
            } else {
                getListener().collapsed();
            }
        }
    }

    private Animator animateItem(View view, float offset) {
        PropertyValuesHolder position = PropertyValuesHolder.ofFloat(TRANSLATION_Y, (!expanded) ? 0 : offset, (!expanded) ? offset : 0);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat(ALPHA, (!expanded) ? 0 : 1);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, position, alpha);
        animator.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
        return animator;
    }

    private Animator createCollapseAnimator(View view, float offset) {
        return animateItem(view, offset);
    }

    private Animator createExpandAnimator(View view, float offset) {
        return animateItem(view, offset);
    }

    @Override
    public void removeView(View view) {
        if (view != null) {
            super.removeView(view);
        }
    }

    @Override
    public void addView(View child) {
        super.addView(child);
    }

    @Override
    public void addView(View child, int width, int height) {
        this.removeView(mMenuButton);
        super.addView(child, width, height);
        configMainButton();
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        this.removeView(mMenuButton);
        super.addView(child, params);
        configMainButton();
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        this.removeView(mMenuButton);
        super.addView(child, index, params);
    }

    //Public methods

    public void setMenuButtonBackgroundTintColor(int color) {
        mTintcolor = color;
        if (mMenuButton != null) {
            mMenuButton.setBackgroundTintList(ColorStateList.valueOf(mTintcolor));
        }
    }

    public void setMenuButtonExpandedImage(Drawable drawable) {
        mIconExpanded = drawable;
    }

    public void setMenuButtonImage(Drawable drawable) {
        mIcon = drawable;
        if (mMenuButton != null) {
            mMenuButton.setImageDrawable(mIcon);
        }
    }

    public void collapseMenu() {
        if (expanded) {
            expanded = false;
            animateMenu();
        }
    }

    public void expandMenu() {
        if (!expanded) {
            expanded = true;
            animateMenu();
        }
    }

    public IOMenuButtonState getListener() {
        return listener;
    }

    public void setListener(IOMenuButtonState listener) {
        this.listener = listener;
    }
}
