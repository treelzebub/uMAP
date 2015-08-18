package com.treelzebub.umap.async

import android.content.Context
import android.os.AsyncTask
import com.treelzebub.umap.api.discogs.model.CollectionFolder
import com.treelzebub.umap.api.discogs.model.CollectionReleases
import com.treelzebub.umap.async.event.CollectionEvent
import com.treelzebub.umap.async.event.CollectionReleasesEvent
import com.treelzebub.umap.auth.RestService
import com.treelzebub.umap.util.BusProvider
import com.treelzebub.umap.util.UserUtils

/**
 * Created by Tre Murillo on 6/6/15
 */
var folders: List<CollectionFolder>? = null

public fun syncCollection(c: Context, fullSync: Boolean) {
    object : AsyncTask<Void, Void, List<CollectionFolder>>() {
        override fun doInBackground(vararg params: Void?): List<CollectionFolder> {
            return RestService.instance.getCollection(UserUtils.usernameFromPrefs(c)).folders
        }

        override fun onPostExecute(result: List<CollectionFolder>) {
            folders = result
            BusProvider.instance.post(CollectionEvent(result))
            if (fullSync) {
                syncCollectionReleases(c)
            }
        }
    }.execute()
}

public fun syncCollectionReleases(c: Context) {
    object : AsyncTask<Void, Void, CollectionReleases>() {
        override fun doInBackground(vararg params: Void?): CollectionReleases {
            return RestService.instance.getCollectionReleases(UserUtils.usernameFromPrefs(c), folders!!.first().id.toString())
        }

        override fun onPostExecute(result: CollectionReleases) {
            BusProvider.instance.post(CollectionReleasesEvent(result))
        }
    }.execute()
}