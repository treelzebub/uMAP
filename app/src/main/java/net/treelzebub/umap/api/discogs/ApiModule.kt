package net.treelzebub.umap.api.discogs

import net.treelzebub.umap.Constants
import retrofit.Retrofit
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

/**
 * Created by Tre Murillo on 10/11/15
 */
public class ApiModule {

    private val api: DiscogsApi

    constructor(token: String, tokenSecret: String) {
        val consumer = OkHttpOAuthConsumer(Constants.DISCOGS_CONSUMER_KEY, Constants.DISCOGS_CONSUMER_SECRET)
        consumer.setTokenWithSecret(token, tokenSecret)
        val restAdapter = Retrofit.Builder()
                .baseUrl(Constants.DISCOGS_BASE_URL)
                .client(SigningOkClient(consumer))
                .build()
        api = restAdapter.create(DiscogsApi::class.java)
    }

    public operator fun get(): DiscogsApi {
        return api
    }
}
