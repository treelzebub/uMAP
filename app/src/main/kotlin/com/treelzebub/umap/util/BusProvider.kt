package com.treelzebub.umap.util

/**
 * Created by Tre Murillo on 5/28/15
 */
public object BusProvider {
    private var bus: UMapBus? = null
    public val instance: UMapBus
        get() = bus ?: UMapBus()
}
