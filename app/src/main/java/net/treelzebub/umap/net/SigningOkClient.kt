package net.treelzebub.umap.net

import net.treelzebub.autograph.AutographInterceptor
import oauth.signpost.OAuthConsumer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 10/11/15
 */
class SigningOkClient {
    companion object {
        fun create(consumer: OAuthConsumer, token: Token): OkHttpClient {
            consumer.setTokenWithSecret(token.token, token.secret)
            return OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                    .addInterceptor(AutographInterceptor(consumer))
                    .build()
        }
    }
}