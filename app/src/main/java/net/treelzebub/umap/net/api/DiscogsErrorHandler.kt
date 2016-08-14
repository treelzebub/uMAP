package net.treelzebub.umap.net.api

import android.util.Log
import net.treelzebub.umap.R
import net.treelzebub.umap.android.toast
import net.treelzebub.umap.inject.ContextInjection
import net.treelzebub.umap.net.response.ErrorResponse
import net.treelzebub.umap.util.android.str
import net.treelzebub.umap.util.kotlin.TAG
import retrofit.ErrorHandler
import retrofit.RetrofitError

/**
 * Created by Tre Murillo on 8/13/16
 */
class DiscogsErrorHandler : ErrorHandler {

    override fun handleError(cause: RetrofitError?): Throwable {
        val errorDescription: String
        if (cause == null || cause.response == null) {
            errorDescription = R.string.error_network.str()
        } else if (cause.kind == RetrofitError.Kind.NETWORK) {
            errorDescription = R.string.error_response.str()
        } else if (cause.response.status < 204) {
            errorDescription = handleStatus(cause.response.status)
        } else {
            try {
                val errorResponse = cause.getBodyAs(ErrorResponse::class.java) as ErrorResponse
                errorDescription = errorResponse.error?.data?.message ?: R.string.error_response.str()
            } catch (e: Exception) {
                Log.e(TAG, e.message)
                errorDescription = R.string.error_response.str()
            }
        }
        ContextInjection.c.toast(errorDescription)
        return Exception(errorDescription)
    }

    private fun handleStatus(status: Int): String {
        return when (status) {
            204, 404      -> R.string.error_no_content.str()
            401, 403      -> R.string.error_unauthorized.str()
            /* 405, 422, 500 */
            else          -> R.string.error_unknown.str()
        }
    }
}
