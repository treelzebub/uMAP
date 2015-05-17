package com.treelzebub.umap.auth;

import android.util.Base64;

import com.treelzebub.umap.api.discogs.DiscogsConstants;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.SecureRandom;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

/**
 * Created by Tre Murillo on 4/23/15
 */
public class AuthUtils {

    public static class OAuthHelper {
        private OAuthConsumer mConsumer;
        private OAuthProvider mProvider;
        private String mCallbackUrl;

        public OAuthHelper(String consumerKey, String consumerSecret,
                           String scope, String callbackUrl)
                throws UnsupportedEncodingException {
            mConsumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
            mProvider = new CommonsHttpOAuthProvider(
                    DiscogsConstants.REQUEST_TOKEN_URL,
                    DiscogsConstants.ACCESS_TOKEN_URL,
                    DiscogsConstants.AUTHORIZATION_URL);
            mProvider.setOAuth10a(true);
            mCallbackUrl = (callbackUrl == null ? OAuth.OUT_OF_BAND : callbackUrl);
        }

        public String getRequestToken() throws OAuthMessageSignerException, OAuthNotAuthorizedException,
        OAuthExpectationFailedException, OAuthCommunicationException {
            return mProvider.retrieveRequestToken(mConsumer, mCallbackUrl);
        }
    }

    private static SecureRandom random = new SecureRandom();

    public static String getNonce() {
        return new BigInteger(130, random).toString(32);
    }

    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static String encodeBasicAuthBase64(String rawStr) {
        return "Basic " + Base64.encodeToString(rawStr.getBytes(), Base64.NO_WRAP);
    }

    private AuthUtils() { throw new SecurityException(); }
}
