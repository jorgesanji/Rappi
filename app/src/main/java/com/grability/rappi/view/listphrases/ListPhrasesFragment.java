package com.grability.rappi.view.listphrases;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.cronosgroup.core.view.MVPFragment;
import com.grability.rappi.R;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.model.assets.Phrase;
import com.grability.rappi.presenter.listphrases.ListPhrasesPresenter;

import java.util.List;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ListPhrasesFragment extends MVPFragment<ListPhrasesPresenter, ListPhrasesPresenter.View> implements ListPhrasesPresenter.View, ListPhrasesScreen.Listener {

    private ListPhrasesScreen listPhrasesScreen;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.list_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            getPresenter().onAddPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected View getRootView() {
        listPhrasesScreen = new ListPhrasesScreen(getActivity());
        listPhrasesScreen.setListener(this);
        return listPhrasesScreen;
    }


    @Override
    protected ListPhrasesPresenter createPresenter() {
        return new ListPhrasesPresenter(ScreenNavigationHandler.getInstance());
    }

    @Override
    protected ListPhrasesPresenter.View getPresenterView() {
        return this;
    }

    @Override
    protected void onDidAppear() {
        getPresenter().getPhraseList();
    }

    // RecognizerPresenter.View

    @Override
    public void setPhrases(List<Phrase> phrases) {
        listPhrasesScreen.setItems(phrases);
    }

    // RecognizerScreen.Listener

    @Override
    public void onSelectedPhrase(Phrase phrase) {
        getPresenter().onSelectedPhrase(phrase);
    }
}
