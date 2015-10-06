package net.treelzebub.umap.util

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer

/**
 * Created by Tre Murillo on 5/28/15
 */
public class UMapBus : Bus(ThreadEnforcer.ANY) {

    private val mainThread = Handler(Looper.getMainLooper())

    override fun post(event: Any) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event)
        } else {
            mainThread.post { post(event) }
        }
    }

    override fun register(any: Any) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.register(any)
        } else {
            mainThread.post { register(any) }
        }
    }

    override fun unregister(any: Any) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                super.unregister(any)
            } catch (e: IllegalArgumentException) {
                // dont care
            }

        } else {
            mainThread.post { unregister(any) }
        }
    }
}
