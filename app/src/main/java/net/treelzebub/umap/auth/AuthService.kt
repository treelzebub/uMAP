package net.treelzebub.umap.auth

import net.treelzebub.umap.Constants
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token
import org.scribe.oauth.OAuthService

/**
 * Created by Tre Murillo on 8/17/15
 */
object AuthService {

    private var instance: OAuthService? = null

    fun get(): OAuthService {
        if (instance == null) {
            instance = ServiceBuilder()
                            .apiKey(Constants.DISCOGS_CONSUMER_KEY)
                            .apiSecret(Constants.DISCOGS_CONSUMER_SECRET)
                            .callback(Constants.CALLBACK_URL)
                            .provider(DiscogsOauth::class.java)
                            .build()
        }
        return instance!!
    }

    class DiscogsOauth : DefaultApi10a() {

        override fun getRequestTokenEndpoint() = Constants.DISCOGS_REQUEST_TOKEN_URL

        override fun getAccessTokenEndpoint() = Constants.DISCOGS_ACCESS_TOKEN_URL

        override fun getAuthorizationUrl(requestToken: Token) = Constants.DISCOGS_AUTHORIZATION_URL
    }
}