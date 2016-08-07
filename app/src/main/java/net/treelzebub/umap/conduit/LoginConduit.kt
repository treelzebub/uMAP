package net.treelzebub.umap.conduit

import android.net.Uri
import android.os.Bundle
import com.levelmoney.conduit.Conduit
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import net.treelzebub.umap.auth.AuthService
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.auth.user.Users
import org.scribe.model.Token
import org.scribe.model.Verifier

/**
 * Created by Tre Murillo on 8/6/16.
 */
class LoginConduit : Conduit<LoginConduit, Token?>, LoginInteractor {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle?): Token? {
        val url = args?.getString("url") ?: return null
        val data = Uri.parse(url)
        val verifier = Verifier(data.getQueryParameter("oauth_verifier"))
        val accessToken = AuthService.instance.getAccessToken(TokenHolder.getRequestToken(), verifier)
        Users.serializeUser()
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