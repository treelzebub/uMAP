package net.treelzebub.umap.activity.login

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.levelmoney.conduit.Conduit
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import net.treelzebub.umap.Constants
import net.treelzebub.umap.auth.AuthService
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.model.Identity
import net.treelzebub.umap.model.User
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.response.Response
import net.treelzebub.umap.util.kotlin.TAG
import org.scribe.model.Token
import org.scribe.model.Verifier

/**
 * Created by Tre Murillo on 8/6/16
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


class AccessTokenConduit : Conduit<AccessTokenConduit, Token?>, LoginInteractor {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle?): Token? {
        val url = args?.getString("url") ?: return null
        val data = Uri.parse(url)
        val verifierStr = data.getQueryParameter("oauth_verifier") ?: return null
        val verifier = Verifier(verifierStr)
        val accessToken = AuthService.instance.getAccessToken(TokenHolder.getRequestToken(), verifier)
        TokenHolder.setToken(accessToken)
        return accessToken
    }

    override fun load(url: String) {
        val bundle = Bundle().apply { putString("url", url) }
        load(bundle)
    }
}

interface LoginInteractor {
    fun load(url: String)
}

class IdentityConduit : Conduit<IdentityConduit, Response<Identity>> {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle?): Response<Identity>? {
        return Discogs.connect {
            getIdentity()
        }
    }
}

class UserConduit : Conduit<UserConduit, Response<User>> {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle?): Response<User>? {
        val username = args?.getString("username") ?: throw RuntimeException("$TAG: null username passed in bundle")
        return Discogs.connect {
            getUser(username)
        }
    }
}