package com.treelzebub.umap.api.discogs;

import android.util.Log;

import com.treelzebub.umap.api.AuthTools;
import com.treelzebub.umap.api.AuthenticatedSession;
import com.treelzebub.umap.util.ServiceGenerator;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

/**
 * Created by Tre Murillo on 3/29/15
 * <p/>
 * A singleton that describes an authenticated Discogs session
 */
public class Discogs extends AuthenticatedSession {

    private static Discogs instance;
    private static DiscogsApi mDiscogsApi;

    private static TokenResponse accessToken;

    protected Discogs() {
        initRequestTokenService();
    }

    public static Discogs getInstance() {
        return instance == null ? instance = new Discogs() : instance;
    }

    public Response getRequestToken() {
        initRequestTokenService();
        try {
            return mDiscogsApi.getRequestToken();
        } catch (RetrofitError error) {
            if (error.getResponse() == null) {
                Log.e("Retrofit Error", error.toString());
                Log.e("Request URL", error.getUrl());
                Log.e("LocalizedMessage", error.getLocalizedMessage());
            } else {
                String status = Integer.toString(error.getResponse().getStatus());
                List<Header> headers = error.getResponse().getHeaders();

                Log.e("Retrofit Response", error.getResponse().getReason());
                Log.e("Response Status", status);
                for (Header h : headers) {
                    Log.e(h.getName(), h.getValue());
                }
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

    //TODO abstract out if gemm's tokens are similar enough
    public class TokenResponse {
        private String mToken, mTokenSecret;
        private boolean mIsOAuthCallbackConfirmed;

        public TokenResponse(String token, String tokenSecret) {
            // AccessToken response will not contain oauth_callback_confirmed header
            mToken = token;
            mTokenSecret = tokenSecret;
        }

        public TokenResponse(String token, String tokenSecret, boolean oAuthCallbackConfirmed) {
            mToken = token;
            mTokenSecret = tokenSecret;
            mIsOAuthCallbackConfirmed = oAuthCallbackConfirmed;
        }

        public boolean isOAuthCallbackConfirmed() {
            return mIsOAuthCallbackConfirmed;
        }

        public void setOAuthCallbackConfirmed(boolean isOAuthCallbackConfirmed) {
            this.mIsOAuthCallbackConfirmed = isOAuthCallbackConfirmed;
        }

        public String getToken() {
            return mToken;
        }

        public void setToken(String mToken) {
            this.mToken = mToken;
        }

        public String getTokenSecret() {
            return mTokenSecret;
        }

        public void setTokenSecret(String mTokenSecret) {
            this.mTokenSecret = mTokenSecret;
        }
    }
}
