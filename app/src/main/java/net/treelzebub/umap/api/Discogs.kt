package net.treelzebub.umap.api

import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.data.DiscogsResponse

/**
 * Created by Tre Murillo on 6/18/16
 */

object Discogs {

    val api: DiscogsApi get() = ApiModule(TokenHolder.getToken()).api

    @Suppress("UNCHECKED_CAST")
    fun <D : DiscogsResponse> connect(fn: DiscogsApi.() -> DiscogsResponse): D? {
        while (true) {
            val response = api.fn()
            return response as D?
        }
    }
}