package net.treelzebub.umap.api

import net.treelzebub.umap.Constants
import net.treelzebub.umap.net.SigningOkClient
import org.scribe.model.Token
import retrofit.RestAdapter
import retrofit.client.OkClient
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

/**
 * Created by Tre Murillo on 10/11/15
 */
class ApiModule {

    val api: DiscogsApi

    constructor(token: Token?) {
        if (token == null) throw RuntimeException("Null Token passed to ApiModule")
        val consumer = OkHttpOAuthConsumer(Constants.DISCOGS_CONSUMER_KEY, Constants.DISCOGS_CONSUMER_SECRET)
        consumer.setTokenWithSecret(token.token, token.secret)
        val restAdapter = RestAdapter.Builder()
                .setEndpoint(Constants.DISCOGS_BASE_URL)
                .setClient(OkClient(SigningOkClient(consumer)))
                .build()
        api = restAdapter.create(DiscogsApi::class.java)
    }
}
