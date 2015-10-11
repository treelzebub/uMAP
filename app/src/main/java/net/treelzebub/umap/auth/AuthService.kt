package net.treelzebub.umap.auth

import net.treelzebub.umap.Constants
import org.scribe.builder.ServiceBuilder
import org.scribe.oauth.OAuthService

/**
 * Created by Tre Murillo on 8/17/15
 */
public object AuthService {
    public val instance: OAuthService
        get() = ServiceBuilder()
                .apiKey(Constants.DISCOGS_CONSUMER_KEY)
                .apiSecret(Constants.DISCOGS_CONSUMER_SECRET)
                .callback(Constants.CALLBACK_URL)
                .provider(DiscogsOauth::class.java)
                .build()
}
