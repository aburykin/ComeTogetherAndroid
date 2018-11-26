package ru.bur.cometogetherandroid.rest;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class AppUserResponseHandler extends AsyncHttpResponseHandler {

    private String LOG_TAG = "cometogetherandroid";

    @Override
    public void onStart() {
        Log.i(LOG_TAG, "AppUserResponseHandler onStart");
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
        // called when response HTTP status is "200 OK"
        Log.i(LOG_TAG, "AppUserResponseHandler onFailure. statusCode="+statusCode+",  headers="+headers+", response="+response);
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
        Log.i(LOG_TAG, "AppUserResponseHandler onFailure. statusCode="+statusCode+", errorResponse="+errorResponse);

    }

    @Override
    public void onRetry(int retryNo) {
        // called when request is retried
        Log.i(LOG_TAG, "AppUserResponseHandler onRetry");
    }

}
