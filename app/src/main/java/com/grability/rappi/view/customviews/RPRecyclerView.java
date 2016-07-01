package com.grability.rappi.view.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.grability.rappi.R;

/**
 * Created by jorgesanmartin on 11/10/15.
 */
public class RPRecyclerView extends RecyclerView {

    private static final float DEFAULT_FACTOR = 0.7f;

    //Vars
    private float factorVelocity = DEFAULT_FACTOR;

    public RPRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public RPRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RPRecyclerView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray attributes = null;
            try {
                attributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RPRecyclerView);
                setFactorVelocity(attributes.getFloat(R.styleable.RPRecyclerView_factorVelocity, DEFAULT_FACTOR));
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
    public boolean fling(int velocityX, int velocityY) {
        velocityY *= getFactorVelocity();
        return super.fling(velocityX, velocityY);
    }

    public float getFactorVelocity() {
        return factorVelocity;
    }

    public void setFactorVelocity(float factorVelocity) {
        this.factorVelocity = factorVelocity;
    }

}
