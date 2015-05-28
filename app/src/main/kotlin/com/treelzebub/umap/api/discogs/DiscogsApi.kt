package com.treelzebub.umap.api.discogs

import com.treelzebub.umap.api.discogs.constants.*
import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 5/28/15
 * Copyright(c) 2015 Level, Inc.
 */
public class DiscogsApi : DefaultApi10a() {

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
