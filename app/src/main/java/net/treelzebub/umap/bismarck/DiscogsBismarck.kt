package net.treelzebub.umap.bismarck

import android.util.Log
import com.levelmoney.bismarck.impl.BaseBismarck
import net.treelzebub.umap.data.DiscogsResponse
import net.treelzebub.umap.util.kotlin.TAG

/**
 * Created by Tre Murillo on 8/6/16.
 */
class DiscogsBismarck<D : DiscogsResponse>(val name: String? = null) : BaseBismarck<D>() {

    override fun peek(): D? {
        try {
            return super.peek()
        } catch (e: Exception) {
            Log.d(name ?: TAG, "Peek threw an exception: $e")
            invalidate()
            persister?.put(null)
            refresh()
            return null
        }
    }
}
