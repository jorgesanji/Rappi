package com.cronosgroup.core.presenter;


/**
 * Created by jorgesanmartin on 10/30/15.
 */
public class CropPresenter extends BasePresenter<CropPresenter.View> {

    private final CropActions listener;

    /**
     * Crop listeners.
     */
    public interface View extends Presenter.View {
    }

    /**
     * Crop actions.
     */
    public interface CropActions {
    }

    /**
     * @Crop navigationListener
     */
    public CropPresenter(CropActions navigationListener) {
        this.listener = navigationListener;
    }

}
