package com.grability.rappi.presenter.detail;

import android.app.Activity;
import android.os.Bundle;

import com.cronosgroup.core.presenter.Presenter;
import com.grability.rappi.ScreenNavigationHandler;
import com.grability.rappi.model.dataacess.database.model.AppItem;
import com.grability.rappi.presenter.base.RappiPresenter;

import io.realm.Realm;


/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ItemDetailPresenter extends RappiPresenter<ItemDetailPresenter.View> {

    private final Actions listener;

    public ItemDetailPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
        void setAppItem(AppItem appItem);

        String getLink();
    }

    public interface Actions {
        void onLinkPressed(Activity activity, Bundle bundle);
    }

    //Public methods

    public void getDetailItemWithId(String id) {
        Realm realm = Realm.getDefaultInstance();
        getView().setAppItem(realm.where(AppItem.class).equalTo("id", id).findFirst());
    }

    public void onLinkPressed() {
        Bundle bundle = new Bundle();
        bundle.putString(ScreenNavigationHandler.URL, getView().getLink());
        listener.onLinkPressed(getView().getActivity(), bundle);
    }

}
