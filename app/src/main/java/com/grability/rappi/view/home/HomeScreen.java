package com.grability.rappi.view.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.rest.model.RestEntry;
import com.grability.rappi.utils.TLDividerItemDecoration;
import com.grability.rappi.view.customviews.RPRecyclerView;
import com.grability.rappi.view.home.adapter.ListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomeScreen extends RelativeLayout {

    public interface Listener {
        void onItemClickPressed(int position);
    }

    // Vars
    private Listener listener;
    private ListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    // Views
    @BindView(R.id.homelist)
    RPRecyclerView mHomeList;

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
        initRecyclerView();
        initAdapter();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mHomeList.setLayoutManager(mLayoutManager);
        mHomeList.addItemDecoration(new TLDividerItemDecoration(getContext(), R.drawable.line_divider_contacts));
    }

    private void initAdapter() {
        mAdapter = new ListAdapter();
        mHomeList.setAdapter(mAdapter);
    }

    // Actions


    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void addItems(List<RestEntry> entryList) {
        mAdapter.addItems(entryList);
    }

}
