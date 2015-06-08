package com.treelzebub.umap.async.event

import org.scribe.model.Token
import kotlin.com.treelzebub.umap.util.BusProvider
/**
 * Created by Tre Murillo on 6/7/15
 */
public class AccessTokenEvent(var accessToken: Token? = null) : Event {

    override fun onSuccess() {
        BusProvider.getInstance().post(this)
    }

    override fun onFailure() {
//        BusProvider.getInstance().post(LoginFailureEvent())
    }
}
