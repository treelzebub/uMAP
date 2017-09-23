package net.treelzebub.umap.util.android

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.view.View
import android.widget.Toast
import com.levelmoney.bismarck.Bismarck
import net.treelzebub.umap.activity.UmapActivity

/**
 * Created by Tre Murillo on 8/6/16
 */

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

inline fun <reified R : Any> UmapActivity.subscribeToBismarck(b: Bismarck<R>, noinline fn: (R?) -> Unit) {
    subscribe(b.observe(), fn)
}

@Suppress("UNCHECKED_CAST")
fun <T : View> Activity.findById(id: Int): T = findViewById<T>(id)

inline fun Activity.onClick(@IdRes id: Int, crossinline fn: (View) -> Unit) {
    findById<View>(id).setOnClickListener {
        fn(it)
    }
}