package com.grability.rappi.view.listphrases.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.cronosgroup.core.view.recyclerview.BaseViewHolder;
import com.grability.rappi.R;
import com.grability.rappi.model.assets.Phrase;

import butterknife.BindView;

/**
 * Created by jorgesanmartin on 2/26/16.
 */
public class PhraseViewHolder extends BaseViewHolder<Phrase> {

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.subtitle)
    TextView mSubtitle;

    public PhraseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void configureItem(Phrase item) {
        mSubtitle.setText(String.format(itemView.getContext().getString(R.string.list_text), getAdapterPosition()));
    }
}
