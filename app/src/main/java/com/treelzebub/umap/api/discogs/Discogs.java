package com.treelzebub.umap.api.discogs;

import android.util.Log;

import com.treelzebub.umap.api.AuthTools;
import com.treelzebub.umap.api.AuthenticatedSession;
import com.treelzebub.umap.util.ServiceGenerator;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Tre Murillo on 3/29/15
 * <p/>
 * A singleton that describes an authenticated Discogs session
 */
public class Discogs extends AuthenticatedSession {

    private static Discogs instance;
    private static DiscogsApi mDiscogsApi;

    protected Discogs() {
    }

    public static Discogs getInstance() {
        return instance == null ? instance = new Discogs() : instance;
    }

    public Response getRequestToken() {
        initRequestTokenService();
        try {
            return mDiscogsApi.getAuthUrl();
        } catch (RetrofitError error) {
            if (error.getResponse() == null) {
                Log.e("Retrofit Error", error.toString());
                Log.e("Request URL", error.getUrl());
                Log.e("LocalizedMessage", error.getLocalizedMessage());
            } else {
                String status = Integer.toString(error.getResponse().getStatus());

                Log.e("Retrofit Response", error.getResponse().getReason());
                Log.e("Response Status", status);
            }
            return null;
        }
    }

    public void initRequestTokenService() {
        mDiscogsApi = ServiceGenerator.createService(
                DiscogsApi.class, DiscogsConstants.BASE_URL,
                DiscogsConstants.CONSUMER_KEY, AuthTools.getNonce(),
                DiscogsConstants.CONSUMER_SECRET, DiscogsConstants.SIGNATURE_METHOD,
                AuthTools.getTimestamp(), DiscogsConstants.CALLBACK_URL);
    }

}
