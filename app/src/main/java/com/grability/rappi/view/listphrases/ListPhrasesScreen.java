package com.grability.rappi.view.listphrases;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.cronosgroup.core.view.BaseAdapter;
import com.grability.rappi.R;
import com.grability.rappi.model.assets.Phrase;
import com.grability.rappi.utils.TLDividerItemDecoration;
import com.grability.rappi.view.listphrases.adapter.PhrasesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ListPhrasesScreen extends RelativeLayout {

    public interface Listener {
        void onSelectedPhrase(Phrase phrase);
    }

    // Vars
    private Listener listener;
    private PhrasesAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    // Views
    @BindView(R.id.list)
    RecyclerView mList;

    /**
     * @param context
     */
    public ListPhrasesScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public ListPhrasesScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ListPhrasesScreen(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public ListPhrasesScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_listphrases, this);
        ButterKnife.bind(this);
        initRecyclerView();
        initAdapter();
        initListeners();
    }

    private void initRecyclerView() {
        mLayoutManager = new LinearLayoutManager(getContext());
        mList.setLayoutManager(mLayoutManager);
        mList.addItemDecoration(new TLDividerItemDecoration(getContext(), R.drawable.line_divider_contacts));

    }

    private void initAdapter() {
        mAdapter = new PhrasesAdapter();
        mList.setAdapter(mAdapter);
    }

    private void initListeners() {
        mAdapter.setClickListener(new BaseAdapter.CLickListener() {
            @Override
            public void onItemSelected(int position) {
                listener.onSelectedPhrase(mAdapter.getItem(position));
            }
        });
    }

    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void setItems(List<Phrase> phrases) {
        mAdapter.clearData();
        mAdapter.addItems(phrases);
    }
}
