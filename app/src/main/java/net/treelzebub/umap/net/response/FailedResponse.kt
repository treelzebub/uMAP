package net.treelzebub.umap.net.response

/**
 * Created by Tre Murillo on 8/14/16
 */
class FailedResponse<D : Any>(e: Exception) : Response<D> {
    override val data: D? = null
    override val exception: Exception = e
    override val success: Boolean = false
}
