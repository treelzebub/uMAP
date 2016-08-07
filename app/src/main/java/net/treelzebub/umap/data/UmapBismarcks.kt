package net.treelzebub.umap.data

import com.levelmoney.bismarck.Bismarcks
import net.treelzebub.umap.bismarck.discogs

/**
 * Created by Tre Murillo on 8/6/16.
 */
object UmapBismarcks {

    fun register() {
        Data.user       = Bismarcks.discogs("discogs_user")
        Data.collection = Bismarcks.discogs("discogs_user_collection")
    }
}
