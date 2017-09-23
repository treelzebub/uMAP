package net.treelzebub.umap.inject

import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by Tre Murillo on 6/18/16
 */
@Deprecated("this was a horrible idea")
object ContextInjection {

    var c: Context by Delegates.notNull()
}