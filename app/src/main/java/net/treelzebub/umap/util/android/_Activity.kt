package net.treelzebub.umap.util.android

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View
import org.jetbrains.anko.find

/**
 * Created by Tre Murillo on 8/6/16
 */

inline fun Activity.onClick(@IdRes id: Int, crossinline fn: (View) -> Unit) {
    find<View>(id).setOnClickListener {
        fn(it)
    }
}