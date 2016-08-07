package net.treelzebub.umap.api

import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.inject.NetInjection

/**
 * Created by Tre Murillo on 6/18/16
 */

object Discogs {

    val api: DiscogsApi
        get() = NetInjection.apiInjector ?: ApiModule(TokenHolder.getToken()).api

    fun <D> connect(fn: DiscogsApi.() -> D): D? {
        val response = api.fn()
        return response
    }
}