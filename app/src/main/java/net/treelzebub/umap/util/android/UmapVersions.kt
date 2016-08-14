package net.treelzebub.umap.util.android

import net.treelzebub.umap.BuildConfig

/**
 * Created by Tre Murillo on 8/14/16
 */
object UmapVersions {

    fun isDebug(): Boolean {
        return "debug" in BuildConfig.BUILD_TYPE
    }
}