package com.treelzebub.umap.auth;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson.JacksonFactory;
import com.treelzebub.umap.Constants;
import com.treelzebub.umap.api.discogs.DiscogsConstants;
import com.wuman.android.auth.AuthorizationFlow;
import com.wuman.android.auth.AuthorizationUIController;
import com.wuman.android.auth.DialogFragmentController;
import com.wuman.android.auth.OAuthManager;
import com.wuman.android.auth.oauth2.store.SharedPreferencesCredentialStore;

import java.io.IOException;

/**
 * Created by Tre Murillo on 5/16/15
 * Copyright(c) 2015 Level, Inc.
 */
public class DiscogsOAuth {

    private Context mContext;
    private FragmentManager mFragMan;

    public DiscogsOAuth(Context c, FragmentManager fm) {
        mContext = c;
        mFragMan = fm;
        AuthorizationFlow.Builder builder = new AuthorizationFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                AndroidHttp.newCompatibleTransport(),
                new JacksonFactory(),
                new GenericUrl(DiscogsConstants.ACCESS_TOKEN_URL),
                new ClientParametersAuthentication(DiscogsConstants.CONSUMER_KEY, DiscogsConstants.CONSUMER_SECRET),
                DiscogsConstants.CONSUMER_KEY, DiscogsConstants.AUTHORIZATION_URL);
        builder.setTemporaryTokenRequestUrl(DiscogsConstants.REQUEST_TOKEN_URL);
        AuthorizationFlow flow = builder.build();
        AuthorizationUIController controller =
                new DialogFragmentController(mFragMan) {
                    @Override
                    public boolean isJavascriptEnabledForWebView() {
                        return true;
                    }

                    @Override
                    public String getRedirectUri() throws IOException {
                        return Constants.CALLBACK_URL;
                    }
                };
        OAuthManager authMan = new OAuthManager(flow, controller);
        SharedPreferencesCredentialStore credentialStore =
                new SharedPreferencesCredentialStore(mContext,
                        Constants.CREDENTIALS_STORE_PREF_FILE, OAuth.JSON_FACTORY);
        //...
    }
}
