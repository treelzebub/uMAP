package net.treelzebub.umap.net.api

import net.treelzebub.umap.net.SigningOkClient
import oauth.signpost.OAuthConsumer
import org.scribe.model.Token
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by treelzebub on 9/22/2017
 */
object Discogs {

    const val USER_AGENT                 = "Android uMAP/0.001"
    const val CALLBACK_URL               = "oauth://umap.treelzebub.net"
    const val DISCOGS_BASE_URL           = "https://api.discogs.com"
    const val DISCOGS_REQUEST_TOKEN_URL  = DISCOGS_BASE_URL + "/oauth/request_token"
    const val DISCOGS_ACCESS_TOKEN_URL   = DISCOGS_BASE_URL + "/oauth/access_token"
    const val DISCOGS_AUTHORIZATION_URL  = "https://www.discogs.com/oauth/authorize"
    const val DISCOGS_AUTH_URL_APPEND    = "?oauth_token="


    lateinit var api: DiscogsApi
        private set

    fun init(consumer: OAuthConsumer, token: Token) {
        val retrofit = Retrofit.Builder()
                .baseUrl(DISCOGS_BASE_URL)
                .client(SigningOkClient.create(consumer, token))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        api = retrofit.create(DiscogsApi::class.java)
    }
}