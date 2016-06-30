package com.cronosgroup.core.rest;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jorgesanmartin on 14/10/15.
 */
public class RestManager {

    private static final int DEFAULT_TIME_OUT = 40000;
    private static final int CACHE_QUANTITY = 1024 * 1024;
    private static final int TOKEN_EXPIRE = 401;

    private Context mContext;
    private RequestQueue mRequestQueue;
    private String mbaseUrl;
    private Gson mapper = new Gson();
    private SessionStatus sessionStatusListener;

    public RestManager(Context context, String baseUrl) {

        if (context == null) {
            throw new IllegalArgumentException("context must be not null");
        }

        if (baseUrl == null) {
            throw new IllegalArgumentException("baseUrl must be not null");
        }

        this.mContext = context;
        this.mRequestQueue = Volley.newRequestQueue(context, new HurlStack(), CACHE_QUANTITY);
        this.mbaseUrl = baseUrl;
    }

    private void createRequestWithMethod(int method,
                                         String url,
                                         Class clazz,
                                         Map<String, String> params,
                                         Map<String, String> headers,
                                         final Callback callback,
                                         Object tag
    ) {

        final String url_endpoint = mbaseUrl + url;

        Response.Listener<Object> listener = new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {
                Log.d("onResponse", url_endpoint);
                if (callback != null) {
                    if (response != null) {
                        callback.onResponse(response);
                    } else {
                        callback.onErrorResponse(RestError.getMappingError());
                    }
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("onErrorResponse", url_endpoint);
                if (callback != null) {
                    String message = "";
                    int statusCode = 0;
                    if (error.networkResponse != null && error.networkResponse.data != null) {
                        statusCode = error.networkResponse.statusCode;
                        if (statusCode == TOKEN_EXPIRE && getSessionStatusListener() != null) {
                            getSessionStatusListener().endSession();
                            return;
                        } else {
                            String json = new String(error.networkResponse.data);
                            try {
                                JSONObject obj = new JSONObject(json);
                                message = obj.getString("message");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    callback.onErrorResponse(new RestError(statusCode, message));
                }
            }
        };

        if (headers == null)
            headers = new HashMap<String, String>();

        headers.put("Content-Type", "application/json");

        // Allow all SSL certificates
        HttpsTrustManager.allowAllSSL();

        GsonRequest request = new GsonRequest(
                method,
                url_endpoint,
                clazz,
                params,
                headers,
                listener,
                errorListener);

        request.setMapping(getMapper());
        request.setRetryPolicy(new DefaultRetryPolicy(
                DEFAULT_TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        request.setTag(tag);
        addToQueue(request);
    }


    // Public Methods

    public void putRequest(String url, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.PUT, url, clazz, null, null, callback, tag);
    }

    public void putRequest(String url, Map<String, String> params, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.PUT, url, clazz, params, null, callback, tag);
    }

    public void putRequest(String url, Map<String, String> params, Map<String, String> headers, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.PUT, url, clazz, params, headers, callback, tag);
    }

    public void deleteRequest(String url, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.DELETE, url, clazz, null, null, callback, tag);
    }

    public void deleteRequest(String url, Map<String, String> params, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.DELETE, url, clazz, params, null, callback, tag);
    }

    public void deleteRequest(String url, Map<String, String> params, Map<String, String> headers, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.DELETE, url, clazz, params, headers, callback, tag);
    }

    public void getRequest(String url, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.GET, url, clazz, null, null, callback, tag);
    }

    public void getRequest(String url, Map<String, String> params, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.GET, url, clazz, params, null, callback, tag);
    }

    public void getRequest(String url, Map<String, String> params, Map<String, String> headers, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.GET, url, clazz, params, headers, callback, tag);
    }

    public void postRequest(String url, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.POST, url, clazz, null, null, callback, tag);
    }

    public void postRequest(String url, Map<String, String> params, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.POST, url, clazz, params, null, callback, tag);
    }

    public void postRequest(String url, Map<String, String> params, Map<String, String> headers, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.POST, url, clazz, params, headers, callback, tag);
    }

    public void patchRequest(String url, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.PATCH, url, clazz, null, null, callback, tag);
    }

    public void patchRequest(String url, Map<String, String> params, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.PATCH, url, clazz, params, null, callback, tag);
    }

    public void patchRequest(String url, Map<String, String> params, Map<String, String> headers, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.PATCH, url, clazz, params, headers, callback, tag);
    }

    public void traceRequest(String url, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.TRACE, url, clazz, null, null, callback, tag);
    }

    public void traceRequest(String url, Map<String, String> params, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.TRACE, url, clazz, params, null, callback, tag);
    }

    public void traceRequest(String url, Map<String, String> params, Map<String, String> headers, Class clazz, Callback callback, Object tag) {
        createRequestWithMethod(Request.Method.TRACE, url, clazz, params, headers, callback, tag);
    }

    public void addToQueue(Request request) {
        if (isValidQueue()) {
            mRequestQueue.add(request);
        }
    }

    public boolean isValidQueue() {
        return (mRequestQueue != null);
    }

    public void cancelAllRequest() {
        if (isValidQueue()) {
            mRequestQueue.cancelAll(this);
        }
    }

    public void cancelAllRequestWithTag(Object tag) {
        if (isValidQueue()) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public Gson getMapper() {
        return mapper;
    }

    public void setMapper(Gson mapper) {
        this.mapper = mapper;
    }

    public Context getContext() {
        return mContext;
    }

    public RequestQueue getQueue() {
        return mRequestQueue;
    }

    public SessionStatus getSessionStatusListener() {
        return sessionStatusListener;
    }

    public void setSessionStatusListener(SessionStatus sessionStatusListener) {
        this.sessionStatusListener = sessionStatusListener;
    }
}
