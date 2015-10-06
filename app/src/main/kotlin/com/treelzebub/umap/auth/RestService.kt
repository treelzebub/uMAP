package com.treelzebub.umap.auth

import com.treelzebub.umap.DISCOGS_BASE_URL
import com.treelzebub.umap.api.discogs.DiscogsService
import retrofit.RestAdapter
import retrofit.client.OkClient

/**
 * Created by Tre Murillo on 6/9/15
 */
public object RestService {

    public val instance: DiscogsService
        get() = RestAdapter.Builder()
                .setClient(OkClient())
                .setEndpoint(DISCOGS_BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor {
                    it.addHeader("oauth_token", TokenHolder.accessToken?.token)
                }
                .build()
                .create(DiscogsService::class.java)
}
