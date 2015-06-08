package com.treelzebub.umap.async

import android.content.Context
import android.os.AsyncTask
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.DiscogsService
import com.treelzebub.umap.api.discogs.constants.BASE_URL
import com.treelzebub.umap.api.discogs.model.CollectionFolder
import com.treelzebub.umap.api.discogs.model.CollectionReleases
import com.treelzebub.umap.api.discogs.model.User
import com.treelzebub.umap.async.event.CollectionEvent
import com.treelzebub.umap.async.event.CollectionReleasesEvent
import com.treelzebub.umap.async.event.UserEvent
import com.treelzebub.umap.util.BusProvider
import com.treelzebub.umap.util.TokenHolder
import com.treelzebub.umap.util.getPrefs
import retrofit.RestAdapter
import retrofit.client.OkClient

/**
 * Created by Tre Murillo on 6/6/15
 */

var folders: List<CollectionFolder>? = null

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
            BusProvider.getInstance().post(UserEvent(result))
        }
    }.execute()
}

public fun syncCollection(c: Context, fullSync: Boolean) {
    object : AsyncTask<Void, Void, List<CollectionFolder>>() {
        override fun doInBackground(vararg params: Void?): List<CollectionFolder> {
            return getService().getCollection(getUsername(c)).folders
        }

        override fun onPostExecute(result: List<CollectionFolder>) {
            folders = result
            BusProvider.getInstance().post(CollectionEvent(result))
            if (fullSync) {
                syncCollectionReleases(c)
            }
        }
    }.execute()
}

public fun syncCollectionReleases(c: Context) {
    object : AsyncTask<Void, Void, CollectionReleases>() {
        override fun doInBackground(vararg params: Void?): CollectionReleases {
            return getService().getCollectionReleases(getUsername(c), folders!!.first().id.toString())
        }

        override fun onPostExecute(result: CollectionReleases) {
            BusProvider.getInstance().post(CollectionReleasesEvent(result))
        }
    }.execute()
}
