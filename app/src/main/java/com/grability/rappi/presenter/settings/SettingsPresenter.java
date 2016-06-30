package com.grability.rappi.presenter.settings;

import android.content.Intent;

import com.cronosgroup.core.presenter.Presenter;
import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.presenter.base.RappiPresenter;
import com.grability.rappi.view.dialog.addphrase.AddPhraseDialogFragment;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class SettingsPresenter extends RappiPresenter<SettingsPresenter.View> {

    private final Actions listener;

    public SettingsPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
    }

    public interface Actions {

    }

    // BasePresenter
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    //Public methods

    public void onAddPressed() {
        ((MVPFragment) getView().getFragment()).addDialogFragment(AddPhraseDialogFragment.class, Common.ADD_PHRASE_CODE);
    }

}
