package com.treelzebub.umap.auth;

import android.os.AsyncTask;

import com.treelzebub.umap.Constants;
import com.treelzebub.umap.api.discogs.DiscogsConstants;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

/**
 * Created by Tre Murillo on 5/16/15
 * Copyright(c) 2015
 */
public class DiscogsClient {

    /**
     oauth_consumer_key="your_consumer_key",
     oauth_nonce="random_string_or_timestamp",
     oauth_token="oauth_token_received_from_step_2"
     oauth_signature="your_consumer_secret&",
     oauth_signature_method="PLAINTEXT",
     oauth_timestamp="current_timestamp",
     oauth_verifier="users_verifier"
     */

    private String mRequestTokenUrl;

    private OAuthConsumer getConsumer() {
        return new CommonsHttpOAuthConsumer(DiscogsConstants.CONSUMER_KEY, DiscogsConstants.CONSUMER_SECRET);
    }

    public void getRequestToken() {
        new AsyncTask<Void, Void, String>() {
            OAuthProvider provider = new CommonsHttpOAuthProvider(
                    DiscogsConstants.REQUEST_TOKEN_URL,
                    DiscogsConstants.ACCESS_TOKEN_URL,
                    DiscogsConstants.AUTHORIZATION_URL);

            @Override
            protected String doInBackground(Void... params) {
                provider.setOAuth10a(true);
                try {
                    String url = provider.retrieveRequestToken(getConsumer(), Constants.CALLBACK_URL);
                    return url;
                } catch (OAuthCommunicationException | OAuthExpectationFailedException |
                        OAuthNotAuthorizedException | OAuthMessageSignerException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
