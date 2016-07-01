package com.grability.rappi.model.dataacess.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 6/30/16.
 */
public class RestResponse {

    @SerializedName("feed")
    public final RestFeed feed;

    public RestResponse(RestFeed feed){
        this.feed = feed;
    }

    public RestFeed getFeed() {
        return feed;
    }
}
