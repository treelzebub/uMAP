package net.treelzebub.umap.util.bus

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

/**
 * Created by Tre Murillo on 5/28/15
 */
class UMapBus : Bus(com.squareup.otto.ThreadEnforcer.ANY) {

    private val mainThread = Handler(Looper.getMainLooper())

    private val isMainLooper: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    override fun post(event: Any) {
        if (isMainLooper) {
            super.post(event)
        } else {
            mainThread.post { post(event) }
        }
    }

    override fun register(any: Any) {
        if (isMainLooper) {
            super.register(any)
        } else {
            mainThread.post { register(any) }
        }
    }

    override fun unregister(any: Any) {
        if (isMainLooper) {
            try {
                super.unregister(any)
            } catch (ignore: IllegalArgumentException) {
            }

        } else {
            mainThread.post { unregister(any) }
        }
    }
}
