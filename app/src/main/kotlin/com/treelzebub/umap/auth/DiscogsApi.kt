package com.treelzebub.umap.auth

import com.treelzebub.umap.DISCOGS_ACCESS_TOKEN_URL
import com.treelzebub.umap.DISCOGS_AUTHORIZATION_URL
import com.treelzebub.umap.DISCOGS_REQUEST_TOKEN_URL
import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 5/28/15
 */
public class DiscogsApi() : DefaultApi10a() {

    override fun getRequestTokenEndpoint(): String {
        return DISCOGS_REQUEST_TOKEN_URL
    }

    override fun getAccessTokenEndpoint(): String {
        return DISCOGS_ACCESS_TOKEN_URL
    }

    override fun getAuthorizationUrl(requestToken: Token): String {
        return DISCOGS_AUTHORIZATION_URL
    }
}
