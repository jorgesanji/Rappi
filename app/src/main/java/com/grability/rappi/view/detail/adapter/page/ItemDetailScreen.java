package com.grability.rappi.view.detail.adapter.page;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.grability.rappi.R;
import com.grability.rappi.view.customviews.RPImageView;
import com.grability.rappi.view.customviews.RPTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class ItemDetailScreen extends RelativeLayout {

    public interface Listener {

    }

    // Vars
    private Listener listener;

    //properties
    private String appName;
    private String appUrl;
    private String appDescription;
    private String appCategoryName;

    // Views
    @BindView(R.id.appImage)
    RPImageView mAppImage;

    @BindView(R.id.appName)
    RPTextView mAppName;

    @BindView(R.id.appCategoryName)
    RPTextView mAppCategoryName;

    @BindView(R.id.appDescription)
    RPTextView mAppDescription;

    /**
     * @param context
     */
    public ItemDetailScreen(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public ItemDetailScreen(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public ItemDetailScreen(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ItemDetailScreen(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.lay_item_detail, this);
        ButterKnife.bind(this);
    }

    // Actions


    // Public methods

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
        mAppName.setText(appName);
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
        mAppImage.setImageFromUrl(appUrl);
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
        mAppDescription.setText(appDescription);
    }

    public String getAppCategoryName() {
        return appCategoryName;
    }

    public void setAppCategoryName(String appCategoryName) {
        this.appCategoryName = appCategoryName;
        mAppCategoryName.setText(appCategoryName);
    }
}
