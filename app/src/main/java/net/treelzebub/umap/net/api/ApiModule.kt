package net.treelzebub.umap.net.api

import net.treelzebub.umap.Constants
import net.treelzebub.umap.R
import net.treelzebub.umap.net.SigningOkClient
import net.treelzebub.umap.util.android.str
import org.scribe.model.Token
import retrofit.RestAdapter
import retrofit.client.OkClient
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

/**
 * Created by Tre Murillo on 10/11/15
 */
class ApiModule {

    companion object {

        fun from(token: Token?): DiscogsApi {
            return ApiModule(token).api
        }
    }

    private val api: DiscogsApi

    private constructor(token: Token?) {
        if (token == null) throw RuntimeException("Null Token passed to ApiModule")
        val consumer = OkHttpOAuthConsumer(R.string.discogs_consumer_key.str(), R.string.discogs_consumer_secret.str())
        consumer.setTokenWithSecret(token.token, token.secret)
        val signingClient = SigningOkClient(consumer)
        val restAdapter = RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.DISCOGS_BASE_URL)
                .setErrorHandler(DiscogsErrorHandler())
                .setClient(OkClient(signingClient))
                .build()
        api = restAdapter.create(DiscogsApi::class.java)
    }
}
