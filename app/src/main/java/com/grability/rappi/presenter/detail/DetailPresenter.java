package com.grability.rappi.presenter.detail;

import com.cronosgroup.core.presenter.Presenter;
import com.grability.rappi.presenter.base.RappiPresenter;


/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class DetailPresenter extends RappiPresenter<DetailPresenter.View> {

    private final Actions listener;

    public DetailPresenter(Actions actions) {
        this.listener = actions;
    }

    public interface View extends Presenter.View {
    }

    public interface Actions {
    }

    //Public methods


}
