package com.treelzebub.umap.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.treelzebub.umap.R;

import org.scribe.model.Token;

/**
 * Created by Tre Murillo on 5/28/15
 */
public class TokenHolder {

    private static Token requestToken = null;
    private static Token accessToken = null;

    public static Token getRequestToken() {
        return requestToken;
    }

    public static void setRequestToken(Token requestToken) {
        TokenHolder.requestToken = requestToken;
    }

    public static Token getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(Token accessToken) {
        TokenHolder.accessToken = accessToken;
    }

    public static boolean createTokenFromPref(@NonNull Context c) {
        if (accessToken != null) return true;
        SharedPreferences prefs = c.getApplicationContext().getSharedPreferences(c.getString(R.string.key_pref_file), Context.MODE_PRIVATE);
        String key = prefs.getString(c.getString(R.string.key_access_token), null);
        String secret = prefs.getString(c.getString(R.string.key_access_token_secret), null);
        String rawResponse = prefs.getString(c.getString(R.string.key_access_token_raw_response), null);
        if (key == null || secret == null || rawResponse == null) {
            return false;
        }
        setAccessToken(new Token(key, secret, rawResponse));
        return true;
    }

    public static boolean hasAccessToken(@NonNull Context c) {
        return createTokenFromPref(c);
    }

    private TokenHolder() {
    }

    public static void clearTokens() {
        requestToken = null;
        accessToken = null;
    }
}
