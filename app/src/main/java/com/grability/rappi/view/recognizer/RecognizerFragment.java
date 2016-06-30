package com.grability.rappi.view.recognizer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.Commons.Common;
import com.grability.rappi.R;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.events.RecognizerEvent;
import com.grability.rappi.model.assets.Phrase;
import com.grability.rappi.presenter.recognizer.RecognizerPresenter;

import de.greenrobot.event.EventBus;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class RecognizerFragment extends MVPFragment<RecognizerPresenter, RecognizerPresenter.View> implements RecognizerPresenter.View, RecognizerScreen.Listener {

    private RecognizerScreen recognizerScreen;
    private Phrase mPhrase;
    private String mUserAge;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.recognition_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_speak) {
            getPresenter().destroyRecognizer();
            recognizerScreen.startCountDown();
            getPresenter().startRecognizer();
            return true;
        } else if (id == R.id.action_stop) {
            recognizerScreen.stopCountDown();
            getPresenter().destroyRecognizer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected View getRootView() {
        recognizerScreen = new RecognizerScreen(getActivity());
        recognizerScreen.setListener(this);
        return recognizerScreen;
    }

    @Override
    public void onDestroy() {
        getPresenter().destroyRecognizer();
        recognizerScreen.stopCountDown();
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().destroyRecognizer();
        recognizerScreen.stopCountDown();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected RecognizerPresenter createPresenter() {
        return new RecognizerPresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected RecognizerPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidAppear() {
        mPhrase = (Phrase) getArguments().getSerializable(Common.ITEM_SELECTED);
        mUserAge = String.valueOf(getArguments().getInt(Common.KEY_AGE));
        recognizerScreen.setDescription(mPhrase.getInstructions());
        recognizerScreen.setText(mPhrase.getText());
    }

    // RecognizerPresenter.View

    public Phrase getPhrase() {
        return mPhrase;
    }

    @Override
    public void initCount() {
        recognizerScreen.startCountDown();
    }

    @Override
    public String getUserAge() {
        return mUserAge;
    }

    // RecognizerScreen.Listener

    @Override
    public void stopRecognizer() {
        getPresenter().stopRecognizer();
    }

    //region **************  EventBus **************

    public void onEventMainThread(RecognizerEvent event) {
        recognizerScreen.stopCountDown();
        if (!event.hasError()) {
            getPresenter().showResults(event.getResult().get(0));
        } else {
            getPresenter().getStatusView().showRecognitionError();
        }
    }

    //endregion
}
