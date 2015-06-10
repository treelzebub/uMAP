package com.treelzebub.umap.auth

import com.treelzebub.umap.api.discogs.DiscogsService
import com.treelzebub.umap.api.discogs.constants.BASE_URL
import com.treelzebub.umap.util.TokenHolder
import retrofit.RestAdapter
import retrofit.client.OkClient
import kotlin.platform.platformStatic

/**
 * Created by Tre Murillo on 6/9/15
 */
public object RestService {

    private var s: DiscogsService? = null
    private val restAdapter =
            RestAdapter.Builder()
                    .setClient(OkClient())
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor {
                        it.addHeader("oauth_token", TokenHolder.accessToken?.getToken())
                    }
                    .build()

    platformStatic
    public val service: DiscogsService
        get() = s ?: restAdapter.create(javaClass<DiscogsService>())
}