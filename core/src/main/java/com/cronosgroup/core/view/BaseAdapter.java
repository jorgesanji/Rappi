package com.cronosgroup.core.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorgesanmartin on 11/19/15.
 */
public abstract class BaseAdapter<V extends RecyclerView.ViewHolder, I> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    public interface CLickListener {
        void onItemSelected(int position);
    }

    public interface LongPressListener {
        void onItemSelected(int position);
    }

    // Vars
    private List<I> items = new ArrayList<>();
    private List<I> allItems = new ArrayList<>();
    private CLickListener listener;
    private LongPressListener longPresslistener;

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null) {
                List<I> list = filterBy(constraint.toString());
                if (list != null) {
                    results.values = list;
                    results.count = list.size();
                }
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                setItems((List<I>) results.values);
                notifyDataSetChanged();
            }
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return super.convertResultToString(resultValue);
        }
    };

    // Constructor

    public BaseAdapter(List<I> items, boolean filter) {
        setItems(items);
        if (filter) {
            setAllItems(getItems());
        }
    }

    public BaseAdapter(boolean enableFilter) {
        this(new ArrayList<I>(), enableFilter);
    }

    public BaseAdapter() {
        this(new ArrayList<I>(), false);
    }

    // Abstract methods

    public abstract RecyclerView.ViewHolder getHolder(ViewGroup parent, int viewType);

    public abstract void configItem(V holder, int position, boolean isLastItem);

    public abstract List<I> filterBy(String query);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        configItem((V) holder, position, (position == (getItemCount() - 1)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public CLickListener getClickListener() {
        return listener;
    }

    public void setClickListener(CLickListener listener) {
        this.listener = listener;
    }

    public LongPressListener getLongPresslistener() {
        return longPresslistener;
    }

    public void setLongPresslistener(LongPressListener longPresslistener) {
        this.longPresslistener = longPresslistener;
    }

    public List<I> getItems() {
        return items;
    }

    public void setItems(List<I> items) {
        if (items == null) {
            items = new ArrayList<I>();
        }

        this.items = items;
    }

    public List<I> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<I> allItems) {
        this.allItems = allItems;
    }

    public I getItem(int position) {
        return items.get(position);
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private void addedItems(List<I> lastItems) {
        if (lastItems != null && getItems().size() == 0) {
            setItems(new ArrayList<>(lastItems));
        } else if (lastItems != null) {
            getItems().addAll(lastItems);
        }
    }

    public void addItems(List<I> lastItems) {
        int curSize = getItemCount();
        addedItems(lastItems);
        notifyItemRangeInserted(curSize, getItemCount() - 1);
    }

    public void addItem(I item) {
        getItems().add(item);
    }

    public void clearData() {
        getItems().clear();
        notifyDataSetChanged();
    }

    public void removeAndUpdateUI(int position) {
        if (position >= 0 && position < getItems().size()) {
            getItems().remove(position);
            notifyItemRemoved(position);
        }
    }
}
