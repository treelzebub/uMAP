//package net.treelzebub.umap.async
//
//import android.content.Context
//import android.os.AsyncTask
//import net.treelzebub.umap.api.discogs.model.CollectionFolder
//import net.treelzebub.umap.api.discogs.model.CollectionReleases
//import net.treelzebub.umap.async.event.CollectionEvent
//import net.treelzebub.umap.async.event.CollectionReleasesEvent
//import net.treelzebub.umap.auth.RestService
//import net.treelzebub.umap.util.BusProvider
//import net.treelzebub.umap.util.UserUtils
//
///**
// * Created by Tre Murillo on 6/6/15
// */
//var folders: List<CollectionFolder>? = null
//
//fun syncCollection(c: Context, fullSync: Boolean) {
//    object : AsyncTask<Void, Void, List<CollectionFolder>>() {
//        override fun doInBackground(vararg params: Void?): List<CollectionFolder> {
//            return RestService.instance.getCollection(UserUtils.usernameFromPrefs(c)).folders
//        }
//
//        override fun onPostExecute(result: List<CollectionFolder>) {
//            folders = result
//            BusProvider.instance.post(CollectionEvent(result))
//            if (fullSync) {
//                syncCollectionReleases(c)
//            }
//        }
//    }.execute()
//}
//
//fun syncCollectionReleases(c: Context) {
//    object : AsyncTask<Void, Void, CollectionReleases>() {
//        override fun doInBackground(vararg params: Void?): CollectionReleases {
//            return RestService.instance.getCollectionReleases(UserUtils.usernameFromPrefs(c), folders!!.first().id.toString())
//        }
//
//        override fun onPostExecute(result: CollectionReleases) {
//            BusProvider.instance.post(CollectionReleasesEvent(result))
//        }
//    }.execute()
//}