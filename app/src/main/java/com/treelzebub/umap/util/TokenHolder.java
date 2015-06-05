package com.treelzebub.umap.util;

import org.scribe.model.Token;

/**
 * Created by Tre Murillo on 5/28/15
 * Copyright(c) 2015 Level, Inc.
 */
public class TokenHolder {

    private static Token requestToken;
    private static Token accessToken;

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

    public Token createTokenFromPref(String key, String secret, String rawResponse) {
        setAccessToken(new Token(key, secret, rawResponse));
        return getAccessToken();
    }

    private TokenHolder() {
    }
}
