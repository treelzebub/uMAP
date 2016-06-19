package net.treelzebub.umap.sync

import android.content.Context
import android.os.AsyncTask
import net.treelzebub.umap.api.DiscogsService
import net.treelzebub.umap.api.model.Collection
import net.treelzebub.umap.api.model.User
import net.treelzebub.umap.async.event.CollectionEvent
import net.treelzebub.umap.async.event.CollectionReleasesEvent
import net.treelzebub.umap.util.bus.BusProvider
import net.treelzebub.umap.util.android.async
import java.io.*

/**
 * Created by Tre Murillo on 10/11/15
 */
object SyncCenter {

    fun serialize(c: Context, obj: Any, filename: String) {
        val fileOut = c.openFileOutput(filename, Context.MODE_PRIVATE)
        val out = ObjectOutputStream(fileOut)
        try {
            out.writeObject(obj)
        } catch(e: IOException) {
            e.printStackTrace()
        } finally {
            out.close()
            fileOut.close()
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> deserialize(c: Context, filename: String, obj: Class<out T>): T {
        val fileIn = c.openFileInput(filename)
        val inStream = ObjectInputStream(fileIn)
        try {
            return inStream.readObject() as T
        } catch(e: IOException) {
            e.printStackTrace()
        } catch(e: ClassNotFoundException) {
            e.printStackTrace();
        } finally {
            inStream.close()
            fileIn.close()
        }
        throw(InvalidClassException("Invalid class ${obj.simpleName} for filename $filename"))
    }

    fun serializeUser(c: Context) {
        async() {
            val user = DiscogsService.getUser()
            serialize(c, user, "user.umap")
        }
    }

    fun deserializeUser(c: Context): User? {
        try {
            return deserialize(c, "user.umap", User::class.java)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        }
    }

    fun syncCollection() {
        object : AsyncTask<Void, Void, Collection>() {
            override fun doInBackground(vararg params: Void?): Collection {
                return DiscogsService.getCollection()
            }

            override fun onPostExecute(result: Collection) {
                BusProvider.instance.post(CollectionEvent(result.folders))
            }
        }
    }

    fun syncCollectionReleases() {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                BusProvider.instance.register(this)
                val releases = DiscogsService.getCollectionReleases()
                BusProvider.instance.post(CollectionReleasesEvent(releases))
                return null
            }
        }.execute(null)
    }
}
