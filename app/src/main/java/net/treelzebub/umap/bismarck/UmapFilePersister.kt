package net.treelzebub.umap.bismarck

import android.content.Context
import com.levelmoney.bismarck.Serializer
import com.levelmoney.bismarck.android.persisters.AndroidFilePersister
import net.treelzebub.umap.R

/**
 * Created by Tre Murillo on 8/14/16
 */
class UmapFilePersister<T : Any>(
        private val c: Context,
        private val key: String,
        serializer: Serializer<T>
) : AndroidFilePersister<T>(c, serializer) {

    override fun path(): String {
        return c.getString(R.string.umap_path) + "/$key"
    }
}
