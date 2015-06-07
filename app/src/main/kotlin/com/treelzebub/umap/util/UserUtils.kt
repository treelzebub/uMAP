package com.treelzebub.umap.util

import android.os.AsyncTask
import com.treelzebub.umap.api.discogs.Discogs
import com.treelzebub.umap.api.discogs.constants.BASE_URL
import com.treelzebub.umap.api.discogs.model.User
import retrofit.RestAdapter
import retrofit.client.OkClient

/**
 * Created by Tre Murillo on 6/6/15
 * Copyright(c) 2015 Level, Inc.
 */

public fun getUser(): User {
    var user = User()
    object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            val restAdapter = RestAdapter.Builder()
                    .setClient(OkClient())
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor {
                        it.addHeader("oauth_token", TokenHolder.getAccessToken().getToken())
                    }
                    .build()
            val service = restAdapter.create(javaClass<Discogs>())
            user = service.getUser("treelzebub")
            return null
        }
    }.execute()
    return user
}