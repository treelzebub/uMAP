package net.treelzebub.umap.api

import net.treelzebub.umap.api.model.DiscogsResponse
import net.treelzebub.umap.auth.TokenHolder

/**
 * Created by Tre Murillo on 6/18/16
 */

object Api {

    val api: DiscogsApi get() = ApiModule(TokenHolder.getToken()).api

    fun <T : DiscogsResponse> connect(fn: DiscogsApi.() -> DiscogsResponse): T? {
        while (true) {
            val response = api.fn()
            return response as T?
        }
    }
}