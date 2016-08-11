package net.treelzebub.umap.conduit

import com.levelmoney.conduit.IConduit

/**
 * Created by Tre Murillo on 8/7/16.
 */

fun <C : IConduit<C, D>, D : Any> IConduit<C, D>.withSpinner(): C {
    return listener(LoadingConduitListener(activity))
}
