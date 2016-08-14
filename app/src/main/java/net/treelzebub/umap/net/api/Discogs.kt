package net.treelzebub.umap.net.api

import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.inject.NetInjection
import net.treelzebub.umap.net.response.Response

/**
 * Created by Tre Murillo on 6/18/16
 */

object Discogs {

    val api: DiscogsApi
        get() = NetInjection.apiInjector ?: ApiModule.from(TokenHolder.getToken())

    fun <D : Any> connect(fn: DiscogsApi.() -> D): Response<D>? {
        val response = api.fn()
        return object : Response<D> {
            override val data: D? = response
            override val exception: Exception? = null
            override val success: Boolean = data != null
        }
    }
}