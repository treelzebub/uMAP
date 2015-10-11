package net.treelzebub.umap.auth

import net.treelzebub.umap.Constants
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token
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

    public class DiscogsOauth : DefaultApi10a() {
        override fun getRequestTokenEndpoint(): String {
            return Constants.DISCOGS_REQUEST_TOKEN_URL
        }

        override fun getAccessTokenEndpoint(): String {
            return Constants.DISCOGS_ACCESS_TOKEN_URL
        }

        override fun getAuthorizationUrl(requestToken: Token): String {
            return Constants.DISCOGS_AUTHORIZATION_URL
        }
    }
}