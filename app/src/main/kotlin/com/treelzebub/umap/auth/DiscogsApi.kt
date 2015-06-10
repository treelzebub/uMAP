package com.treelzebub.umap.auth

import com.treelzebub.umap.api.discogs.constants.ACCESS_TOKEN_URL
import com.treelzebub.umap.api.discogs.constants.AUTHORIZATION_URL
import com.treelzebub.umap.api.discogs.constants.REQUEST_TOKEN_URL
import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 5/28/15
 */
public class DiscogsApi() : DefaultApi10a() {

    override fun getRequestTokenEndpoint(): String {
        return REQUEST_TOKEN_URL
    }

    override fun getAccessTokenEndpoint(): String {
        return ACCESS_TOKEN_URL
    }

    override fun getAuthorizationUrl(requestToken: Token): String {
        return AUTHORIZATION_URL
    }
}
