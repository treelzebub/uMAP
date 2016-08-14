package net.treelzebub.umap.util.android

import android.os.Build

/**
 * Created by Tre Murillo on 8/6/16.
 */
object AndroidVersions {

    fun isAtLeastL() = Build.VERSION.SDK_INT >= 21

    fun isAtLeastM() = Build.VERSION.SDK_INT >= 23
}