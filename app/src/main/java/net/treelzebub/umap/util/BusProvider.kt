package net.treelzebub.umap.util

/**
 * Created by Tre Murillo on 5/28/15
 */
public object BusProvider {

    public val instance: UMapBus
        get() = UMapBus()
}
