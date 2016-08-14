package net.treelzebub.umap.async

import android.os.Handler

/**
 * Created by Tre Murillo on 8/7/16.
 */

fun post(delay: Long?, fn: () -> Unit) {
    if (delay == null) {
        Handler().post(fn)
    } else {
        Handler().postDelayed(fn, delay)
    }
}

fun post(fn: () -> Unit) {
    post(null, fn)
}