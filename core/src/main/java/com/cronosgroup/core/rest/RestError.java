package com.cronosgroup.core.rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jorgesanmartin on 2/5/16.
 */
public class RestError {

    public static final int ERROR_CODE_PARSING = 2003;
    public static final String ERROR_PARSING_MESSAGE = "Error gson mapping";

    public static final int ERROR_REQUEST_CODE_SERVER = 2004;
    public static final String ERROR_REQUEST_MESSAGE = "Error request server";

    @SerializedName("code")
    private int code;

    @SerializedName("description")
    private String description;

    public RestError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hasError() {
        return getCode() != 0;
    }


    public static RestError getRequestError() {
        return new RestError(ERROR_REQUEST_CODE_SERVER, ERROR_REQUEST_MESSAGE);
    }

    public static RestError getMappingError() {
        return new RestError(ERROR_REQUEST_CODE_SERVER, ERROR_REQUEST_MESSAGE);
    }
}
