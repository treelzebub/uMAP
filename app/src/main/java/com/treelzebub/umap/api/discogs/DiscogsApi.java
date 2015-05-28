package com.treelzebub.umap.api.discogs;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Created by Tre Murillo 1/27/2015
 */
public class DiscogsApi extends DefaultApi10a {

    @Override
    public String getRequestTokenEndpoint() {
        return DiscogsConstants.REQUEST_TOKEN_URL;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return DiscogsConstants.ACCESS_TOKEN_URL;
    }

    @Override
    public String getAuthorizationUrl(Token requestToken) {
        return DiscogsConstants.AUTHORIZATION_URL;
    }
}
