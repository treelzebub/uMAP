package net.treelzebub.umap.conduit

import android.os.Bundle
import android.util.Log
import com.levelmoney.conduit.Conduit
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import net.treelzebub.umap.Constants
import net.treelzebub.umap.auth.AuthService
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.util.kotlin.TAG

/**
 * Created by Tre Murillo on 8/6/16.
 */
class RequestTokenConduit : Conduit<RequestTokenConduit, String?>, RequestTokenInteractor {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle?): String? {
        val auth = AuthService.instance
        val rt = auth.requestToken
        TokenHolder.setRequestToken(rt)
        try {
            val authUrl = auth.getAuthorizationUrl(rt) + Constants.DISCOGS_AUTH_URL_APPEND + rt.token
            return authUrl
        } catch(e: Exception) {
            Log.e(TAG, e.message)
            return null
        }
    }

    override fun load() {
        load(null)
    }
}

interface RequestTokenInteractor {
    fun load()
}
