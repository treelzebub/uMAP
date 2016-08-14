package net.treelzebub.umap.conduit

import com.levelmoney.conduit.IConduit
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.net.response.Response

/**
 * Created by Tre Murillo on 8/13/16
 */

fun <C: IConduit<C, Response<D>>, D : Any> IConduit<C, Response<D>>.onSuccess(fn: (D?) -> Unit): C {
    return onComplete {
        if (it.success) {
            fn(it.data)
        } else {
            activity.let {
                if (it is UmapActivity) {
                    it.checkRelogin()
                }
            }
        }
    }
}

fun <C: IConduit<C, Response<D>>, D : Any> IConduit<C, Response<D>>.onFailure(fn: (Response<D>) -> Unit): C {
    return onComplete {
        if (!it.success) {
            fn(it)
        }
    }
}

fun <C : IConduit<C, D>, D : Any> IConduit<C, D>.withSpinner(): C {
    return listener(LoadingConduitListener(activity))
}
