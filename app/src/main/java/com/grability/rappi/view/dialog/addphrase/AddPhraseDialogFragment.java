package com.grability.rappi.view.dialog.addphrase;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grability.rappi.Commons.Common;

/**
 * Created by jorgesanmartin on 10/26/15.
 */
public class AddPhraseDialogFragment extends AppCompatDialogFragment implements AddPhraseDialogScreen.Listener {

    private AddPhraseDialogScreen studyDialogScreen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        studyDialogScreen = new AddPhraseDialogScreen(getActivity());
        studyDialogScreen.setListener(this);
        return studyDialogScreen;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onAddPressed() {
        sendResult();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        AddPhraseDialogFragment prev = (AddPhraseDialogFragment) getFragmentManager().findFragmentByTag(AddPhraseDialogFragment.class.toString());
        if (prev != null) {
            ft.remove(prev);
            prev.dismiss();
        }
        ft.addToBackStack(null);
    }

    private void sendResult() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Common.ADD_PHRASE_KEY, studyDialogScreen.getPrhase());
        Intent intent = new Intent();
        intent.putExtras(bundle);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(), Common.ADD_PHRASE_CODE, intent);

    }
}
