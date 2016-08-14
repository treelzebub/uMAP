package net.treelzebub.umap.net.response

/**
 * Created by Tre Murillo on 8/13/16
 */
interface Response<R : Any> {

    val data: R?
    val exception: Exception?
    val success: Boolean
}
