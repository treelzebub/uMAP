package net.treelzebub.umap.auth

import net.treelzebub.umap.R
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.util.android.str
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
                            .apiKey(R.string.discogs_consumer_key.str())
                            .apiSecret(R.string.discogs_consumer_secret.str())
                            .callback(Discogs.CALLBACK_URL)
                            .provider(DiscogsOauth::class.java)
                            .build()
        }
        return instance!!
    }

    class DiscogsOauth : DefaultApi10a() {
        override fun getRequestTokenEndpoint() = Discogs.DISCOGS_REQUEST_TOKEN_URL
        override fun getAccessTokenEndpoint() = Discogs.DISCOGS_ACCESS_TOKEN_URL
        override fun getAuthorizationUrl(requestToken: Token) = Discogs.DISCOGS_AUTHORIZATION_URL
    }
}