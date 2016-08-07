package net.treelzebub.umap.data

import java.io.Serializable

/**
 * Created by Tre Murillo on 8/6/16.
 */
interface DiscogsResponse<D : Any> : Serializable {
    val data: D?
    val success: Boolean
    val exception: Exception?
}
