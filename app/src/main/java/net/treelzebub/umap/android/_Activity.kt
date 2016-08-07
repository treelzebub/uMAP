package net.treelzebub.umap.android

import android.app.Activity
import android.widget.Toast

/**
 * Created by Tre Murillo on 8/6/16.
 */

fun Activity.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}