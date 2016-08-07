package net.treelzebub.umap.conduit

import android.net.Uri
import android.os.Bundle
import com.levelmoney.conduit.Conduit
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import net.treelzebub.umap.api.Discogs
import net.treelzebub.umap.auth.AuthService
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.data.Data
import org.scribe.model.Verifier

/**
 * Created by Tre Murillo on 8/6/16.
 */
class LoginConduit : Conduit<LoginConduit, Boolean>, LoginInteractor {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle?): Boolean {
        val url = args?.getString("url") ?: return false
        val data = Uri.parse(url)
        val verifier = Verifier(data.getQueryParameter("oauth_verifier"))
        val accessToken = AuthService.instance.getAccessToken(TokenHolder.getRequestToken(), verifier)
        TokenHolder.setToken(accessToken)

        val id = Discogs.connect { getIdentity() }
        if (id != null) {
            Discogs.connect {
                getUser(id.username)
            }.let {
                Data.user.insert(it)
            }
        } else {
            return false
        }

        return true
    }

    override fun load(url: String) {
        val bundle = Bundle().apply { putString("url", url) }
        load(bundle)
    }
}

interface LoginInteractor {
    fun load(url: String)
}