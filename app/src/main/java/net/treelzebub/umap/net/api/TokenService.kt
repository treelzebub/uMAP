package net.treelzebub.umap.net.api

import android.content.Context
import net.treelzebub.umap.R
import org.scribe.builder.ServiceBuilder
import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token
import org.scribe.model.Verifier

/**
 * Created by treelzebub on 9/22/2017
 */
class TokenService(context: Context) {

    private val service = ServiceBuilder()
            .apiKey(context.getString(R.string.discogs_consumer_key))
            .apiSecret(context.getString(R.string.discogs_consumer_secret))
            .callback(Discogs.CALLBACK_URL)
            .provider(DiscogsOauth::class.java)
            .build()

    val requestToken: Token
        get() = service.requestToken

    fun getAuthUrl(token: Token): String = service.getAuthorizationUrl(token)

    fun getAccessToken(requestToken: Token, verifier: Verifier): Token {
        return service.getAccessToken(requestToken, verifier)
    }

    private class DiscogsOauth : DefaultApi10a() {
        override fun getRequestTokenEndpoint()
                = Discogs.DISCOGS_REQUEST_TOKEN_URL
        override fun getAccessTokenEndpoint()
                = Discogs.DISCOGS_ACCESS_TOKEN_URL
        override fun getAuthorizationUrl(token: Token)
                = Discogs.DISCOGS_AUTHORIZATION_URL + Discogs.DISCOGS_AUTH_URL_APPEND + token.token
    }
}