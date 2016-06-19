package net.treelzebub.umap.inject

import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by Tre Murillo on 6/18/16
 */

object ContextInjection {

    var c: Context by Delegates.notNull()
}