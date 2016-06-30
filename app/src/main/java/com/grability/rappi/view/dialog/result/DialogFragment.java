package com.grability.rappi.view.dialog.result;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grability.rappi.R;
import com.grability.rappi.model.RecognitionResult;

/**
 * Created by jorgesanmartin on 10/26/15.
 */
public class DialogFragment extends AppCompatDialogFragment {

    public static final String ITEM_RESULT = "result";

    private RecognitionResult recognitionResult;
    private DialogScreen dialogScreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dialogScreen = new DialogScreen(getActivity());
        return dialogScreen;
    }

    @Override
    public void onStart() {
        super.onStart();
        recognitionResult = (RecognitionResult) getArguments().getSerializable(ITEM_RESULT);
        dialogScreen.setText(recognitionResult.getResult());
        dialogScreen.setResult("Numero de palabras leídas :" + recognitionResult.getNumberWords() + "\n" + "Número de palarbras incorrectos :" + recognitionResult.getIncorrectWords() + "\n" + "Número de palarbras correctos :" + recognitionResult.getCorrectWords());
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }


}
