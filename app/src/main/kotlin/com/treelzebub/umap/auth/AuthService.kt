package com.treelzebub.umap.auth

import com.treelzebub.umap
import com.treelzebub.umap.DISCOGS_CONSUMER_KEY
import com.treelzebub.umap.DISCOGS_CONSUMER_SECRET
import org.scribe.builder.ServiceBuilder
import org.scribe.oauth.OAuthService

/**
 * Created by Tre Murillo on 8/17/15
 */
public object AuthService {
    public val instance: OAuthService
        get() = ServiceBuilder()
            .apiKey(DISCOGS_CONSUMER_KEY)
            .apiSecret(DISCOGS_CONSUMER_SECRET)
            .callback(umap.CALLBACK_URL)
            .provider(javaClass<DiscogsApi>())
            .build()
}
