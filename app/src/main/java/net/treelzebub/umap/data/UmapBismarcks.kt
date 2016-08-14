package net.treelzebub.umap.data

import android.content.Context
import com.levelmoney.bismarck.Bismarcks
import net.treelzebub.umap.bismarck.api

/**
 * Created by Tre Murillo on 8/6/16
 */
object UmapBismarcks {

    fun register(c: Context) {
        Data.user       = Bismarcks.api(c, "umap_user")
        Data.collection = Bismarcks.api(c, "umap_user_collection")
        Data.lastMaster = Bismarcks.api(c, "umap_last_master")
    }
}
