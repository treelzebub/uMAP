package net.treelzebub.umap.util.android

import android.app.Activity
import android.content.Intent
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by Tre Murillo on 6/18/16
 */

class IntentProperty<T>(private val init: (Intent) -> T) : ReadOnlyProperty<Activity, T> {
    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return init(thisRef.intent)
    }
}

fun <T> Activity.withIntent(init: (Intent) -> T) = IntentProperty(init)
