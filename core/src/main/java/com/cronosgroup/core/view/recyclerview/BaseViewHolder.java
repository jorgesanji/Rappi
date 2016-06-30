package com.cronosgroup.core.view.recyclerview;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cronosgroup.core.view.BaseAdapter;

/**
 * Created by jorgesanmartin on 12/1/15.
 */
public class BaseViewHolder<I> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private BaseAdapter.CLickListener cLickListener;
    private BaseAdapter.LongPressListener longPressListener;

    public BaseViewHolder(View itemView) {
        this(itemView, true, false);
    }

    public BaseViewHolder(View itemView, boolean isClickable, boolean longPress) {
        super(itemView);
        if (isClickable) {
            itemView.setOnClickListener(this);
        }

        if (longPress) {
            itemView.setOnLongClickListener(this);
        }
    }

    public BaseAdapter.CLickListener getcLickListener() {
        return cLickListener;
    }

    public BaseViewHolder setClickListener(BaseAdapter.CLickListener listener) {
        this.cLickListener = listener;
        return this;
    }

    public BaseAdapter.LongPressListener getLongPressListener() {
        return longPressListener;
    }

    public void setLongPressListener(BaseAdapter.LongPressListener longPressListener) {
        this.longPressListener = longPressListener;
    }

    @Override
    public void onClick(View v) {
        if (getcLickListener() != null) {
            getcLickListener().onItemSelected(getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (getLongPressListener() != null) {
            Vibrator vibrator = (Vibrator) itemView.getContext().getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(250);
            getLongPressListener().onItemSelected(getAdapterPosition());
        }
        return false;
    }

    public void configureItem(I item) {
    }
}
