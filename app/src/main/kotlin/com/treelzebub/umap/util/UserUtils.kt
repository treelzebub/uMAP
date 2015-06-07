package com.treelzebub.umap.util

import android.os.AsyncTask
import com.treelzebub.umap.api.discogs.DiscogsService
import com.treelzebub.umap.api.discogs.constants.BASE_URL
import com.treelzebub.umap.api.discogs.model.User
import com.treelzebub.umap.async.event.UserEvent
import retrofit.RestAdapter
import retrofit.client.OkClient
import kotlin.com.treelzebub.umap.util.BusProvider

/**
 * Created by Tre Murillo on 6/6/15
 */

public fun syncUser() {
    object : AsyncTask<Void, Void, User>() {
        override fun doInBackground(vararg params: Void?): User? {
            val restAdapter = RestAdapter.Builder()
                    .setClient(OkClient())
                    .setEndpoint(BASE_URL)
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setRequestInterceptor {
                        it.addHeader("oauth_token", TokenHolder.getAccessToken().getToken())
                    }
                    .build()
            val service = restAdapter.create(javaClass<DiscogsService>())
            return service.getUser("treelzebub")
        }

        override fun onPostExecute(result: User?) {
            BusProvider.getInstance().post(UserEvent(result))
        }
    }.execute()
}