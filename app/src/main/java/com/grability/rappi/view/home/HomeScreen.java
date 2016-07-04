package com.grability.rappi.view.home;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.cronosgroup.core.view.BaseAdapter;
import com.cronosgroup.core.view.animator.SlideInUpAnimator;
import com.grability.rappi.R;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.utils.TLDividerItemDecoration;
import com.grability.rappi.view.animation.ScrollHandler;
import com.grability.rappi.view.customviews.RPMenuButton;
import com.grability.rappi.view.customviews.RPRecyclerView;
import com.grability.rappi.view.home.adapter.ListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class HomeScreen extends RelativeLayout {

    public interface Listener {
        void onItemClickPressed(int position);

        void onCategoriesPressed();

        void onApplicationPressed();
    }

    // Vars
    private Listener listener;
    private ListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ScrollHandler mScrollHandler;

    // Views
    @BindView(R.id.homelist)
    RPRecyclerView mHomeList;

    @BindView(R.id.menuView)
    RPMenuButton mMenuButton;

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
        initAdapter(false);
        initListeners();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mHomeList.setLayoutManager(mLayoutManager);
        mHomeList.addItemDecoration(new TLDividerItemDecoration(getContext(), R.drawable.line_divider_contacts));
        mHomeList.setItemAnimator(new SlideInUpAnimator());
        mScrollHandler = new ScrollHandler(mMenuButton, null, null);
        mHomeList.setOnScrollListener(mScrollHandler);
    }

    private void initAdapter(boolean isCategory) {
        mAdapter = new ListAdapter();
        mAdapter.setCategories(isCategory);
        mHomeList.setAdapter(mAdapter);
    }

    private void initListeners() {
        mAdapter.setClickListener(new BaseAdapter.CLickListener() {
            @Override
            public void onItemSelected(int position) {
                listener.onItemClickPressed(position);
            }
        });
    }

    // Actions

    @OnClick(R.id.applicationsbt)
    protected void btnApplications() {
        mMenuButton.collapseMenu();
        listener.onApplicationPressed();
    }

    @OnClick(R.id.categoriesbt)
    protected void btnCategories() {
        mMenuButton.collapseMenu();
        listener.onCategoriesPressed();
    }


    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void addItems(List<AppItem> entryList) {
        mAdapter.addItems(entryList);
    }

    public List<AppItem> getItems() {
        return mAdapter.getItems();
    }

    public void setCategory(boolean isCategory, List<AppItem> items) {
        initAdapter(isCategory);
        mAdapter.setItems(items);
        if (!isCategory) {
            initListeners();
        }
    }

}
