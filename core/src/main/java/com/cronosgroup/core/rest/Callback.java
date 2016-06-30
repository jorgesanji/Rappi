package com.cronosgroup.core.rest;

/**
 * Created by jorgesanmartin on 15/10/15.
 */
public interface Callback<T, E> {

    void onResponse(T response);

    void onErrorResponse(E error);
}
