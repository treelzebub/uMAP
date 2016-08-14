package net.treelzebub.umap.bismarck

import android.util.Log
import com.levelmoney.bismarck.impl.BaseBismarck
import com.levelmoney.bismarck.impl.DedupingBismarck
import com.levelmoney.bismarck.impl.Fetch
import net.treelzebub.umap.util.kotlin.TAG
import java.io.Serializable

/**
* Created by Tre Murillo on 8/6/16
*/
class DiscogsBismarck<D : Serializable>(val name: String? = null) : DedupingBismarck<D>() {

    private var lastKill: Long? = null

    override fun onFetchEnd(fetch: Fetch<D>): Boolean {
        return lastKill.let { it == null || it < fetch.created }
    }

    // on logout or after running tests
    override fun clear() {
        lastKill = System.currentTimeMillis()
        super.clear()
    }

    override fun peek(): D? {
        try {
            return super.peek()
        } catch (e: Exception) {
            Log.d(name ?: "$TAG: ${name?:"no-name"}", "Peek threw an exception: $e")
            invalidate()
            persister?.put(null)
            refresh()
            return null
        }
    }
}
