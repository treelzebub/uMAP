package com.treelzebub.umap.async

import android.content.Context
import android.os.AsyncTask
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.DiscogsService
import com.treelzebub.umap.api.discogs.constants.BASE_URL
import com.treelzebub.umap.api.discogs.model.CollectionReleases
import com.treelzebub.umap.api.discogs.model.User
import com.treelzebub.umap.async.event.CollectionReleasesEvent
import com.treelzebub.umap.async.event.UserEvent
import com.treelzebub.umap.util.TokenHolder
import com.treelzebub.umap.util.getPrefs
import retrofit.RestAdapter
import retrofit.client.OkClient
import kotlin.com.treelzebub.umap.util.BusProvider
import kotlin.com.treelzebub.umap.util.BusProvider.getInstance

/**
 * Created by Tre Murillo on 6/6/15
 */

fun getService(): DiscogsService {
    val restAdapter = RestAdapter.Builder()
            .setClient(OkClient())
            .setEndpoint(BASE_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .setRequestInterceptor {
                it.addHeader("oauth_token", TokenHolder.getAccessToken().getToken())
            }
            .build()
    return restAdapter.create(javaClass<DiscogsService>())
}

fun persistUsername(c: Context, u: User) {
    getPrefs(c)?.edit()?.putString(c.getString(R.string.key_username), u.username)?.commit()
}

fun getUsername(c: Context): String {
    return getPrefs(c)?.getString(c.getString(R.string.key_username), "null") ?: "null"
}

public fun syncUser() {
    object : AsyncTask<Void, Void, User>() {
        override fun doInBackground(vararg params: Void?): User {
            val service = getService()
            return service.getUser("treelzebub")
        }

        override fun onPostExecute(result: User) {
            getInstance().post(UserEvent(result))
        }
    }.execute()
}

public fun syncCollection(c: Context) {
//    object : AsyncTask<Void, Void, CollectionReleases>() {
//        override fun doInBackground(vararg params: Void?): CollectionReleases {
//            return getService().getCollectionReleases(getUsername(c), folderId)
//        }
//
//        override fun onPostExecute(result: CollectionReleases) {
//            BusProvider.getInstance().post(CollectionReleasesEvent(result))
//        }
//    }
}
