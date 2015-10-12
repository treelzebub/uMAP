package net.treelzebub.umap.api.discogs

import net.treelzebub.umap.Constants
import org.scribe.model.Token
import retrofit.RestAdapter
import retrofit.client.OkClient
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

/**
 * Created by Tre Murillo on 10/11/15
 */
public class ApiModule {

    private val api: DiscogsApi

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

    public operator fun get(): DiscogsApi {
        return api
    }
}
