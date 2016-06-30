package com.grability.rappi.presenter.listphrases;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cronosgroup.core.presenter.Presenter;
import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.model.assets.Phrase;
import com.grability.rappi.presenter.base.RappiPresenter;
import com.grability.rappi.view.dialog.addphrase.AddPhraseDialogFragment;

import java.util.List;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ListPhrasesPresenter extends RappiPresenter<ListPhrasesPresenter.View> {

    private final Actions listener;

    public ListPhrasesPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
        void setPhrases(List<Phrase> list);
    }

    public interface Actions {
        void onSelectedPhrase(Activity activity, Bundle bundle);
    }

    // BasePresenter
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    //Public methods

    public void onAddPressed() {
        ((MVPFragment) getView().getFragment()).addDialogFragment(AddPhraseDialogFragment.class, Common.ADD_PHRASE_CODE);
    }

    public void getPhraseList() {
    }

    public void onSelectedPhrase(Phrase phrase) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Common.ITEM_SELECTED, phrase);
        bundle.putInt(Common.KEY_AGE, getView().getFragment().getArguments().getInt(Common.KEY_AGE));
        listener.onSelectedPhrase(getView().getActivity(), bundle);
    }

}
