package net.treelzebub.umap.auth

import net.treelzebub.umap.DISCOGS_BASE_URL
import net.treelzebub.umap.api.discogs.DiscogsApi
import retrofit.RestAdapter
import retrofit.client.OkClient

/**
 * Created by Tre Murillo on 6/9/15
 */
public object RestService {

    public val instance: DiscogsApi
        get() = RestAdapter.Builder()
                .setClient(OkClient())
                .setEndpoint(DISCOGS_BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor {
                    it.addHeader("oauth_token", TokenHolder.accessToken?.token)
                    it.addHeader("oauth_token_secret", TokenHolder.accessToken?.secret)
                }
                .build()
                .create(DiscogsApi::class.java)
}
