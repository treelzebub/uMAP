package com.treelzebub.umap.util

import android.util.Log
import com.treelzebub.umap.util.UMapBus

/**
 * Created by Tre Murillo on 5/28/15
 */
public object BusProvider {
    var bus: UMapBus? = null
    val getInstance : UMapBus
        get() = bus?:UMapBus()
}
