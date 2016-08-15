package net.treelzebub.umap.net.response

import android.util.Log
import net.treelzebub.umap.util.kotlin.TAG

/**
 * Created by Tre Murillo on 8/14/16
 */

inline fun <D: Any> Response<D>.onComplete(fn: (Response<D>) -> Unit): Response<D> {
    try {
        fn(this)
    } catch (e: Exception) {
        Log.e(TAG, "FAILURE: ", e)
        return FailedResponse(e)
    }
    return this
}

inline fun <D: Any> Response<D>.onSuccess(fn: (D?) -> Unit): Response<D> {
    return onComplete { if (success) fn(data) }
}

inline fun <D: Any> Response<D>.onFailure(fn: (Response<D>) -> Unit): Response<D> {
    return onComplete { if (!success) fn(this) }
}