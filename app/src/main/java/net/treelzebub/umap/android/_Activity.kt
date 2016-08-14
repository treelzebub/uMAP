package net.treelzebub.umap.android

import android.content.Context
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
