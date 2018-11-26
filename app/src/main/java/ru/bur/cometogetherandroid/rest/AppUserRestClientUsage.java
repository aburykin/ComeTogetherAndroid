package ru.bur.cometogetherandroid.rest;

import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.*;
import com.loopj.android.http.*;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class AppUserRestClientUsage {

    /*
    public void getPublicTimeline() throws JSONException {
        AppUserRestClient.get("", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                JSONObject firstEvent = timeline.get(0);
                String tweetText = firstEvent.getString("text");

                // Do something with the response
                System.out.println(tweetText);
            }
        });
    }
    */
/*
    public void getAllAppUsers() {
        AppUserRestClient.get("", null, null);
    }
    */
}
