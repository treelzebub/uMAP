package com.treelzebub.umap.auth;

import com.treelzebub.umap.api.discogs.DiscogsConstants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.signature.OAuthMessageSigner;

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

    private AuthUtils() { throw new SecurityException(); }
}
