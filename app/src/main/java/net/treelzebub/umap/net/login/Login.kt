package net.treelzebub.umap.net.login

import android.content.Context
import android.net.Uri
import android.util.Log
import io.reactivex.Observable
import net.treelzebub.autograph.OauthConsumer
import net.treelzebub.umap.R
import net.treelzebub.umap.model.Identity
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.api.TokenService
import net.treelzebub.umap.util.TAG
import net.treelzebub.umap.util.rx.umap
import org.scribe.model.Token
import org.scribe.model.Verifier

/**
 * Created by treelzebub on 9/22/2017
 */
class Login(private val context: Context) {

    companion object {
        private const val VERIFIER_KEY = "oauth_verifier"
    }

    private val service = TokenService(context.applicationContext)

    private var requestToken: Token? = null

    fun getAuthUrl(): String {
        val token = service.requestToken
        requestToken = token
        return service.getAuthUrl(token)
    }

    fun complete(url: String): Observable<Identity> {
        val token = getAccessToken(url)
        initRetrofit(token)
        return getIdentity()
    }

    private fun getAccessToken(url: String): Token {
        Log.d(TAG, "Getting Access Token.")
        val data = Uri.parse(url)
        val verifierParam = data.getQueryParameter(VERIFIER_KEY)!!
        val verifier = Verifier(verifierParam)
        return service.getAccessToken(requestToken!!, verifier)
    }

    private fun initRetrofit(token: Token) {
        Log.d(TAG, "Initializing Retrofit")
        val key = context.getString(R.string.discogs_consumer_key)
        val secret = context.getString(R.string.discogs_consumer_secret)
        val consumer = OauthConsumer(key, secret)
        Discogs.init(consumer, token)
    }

    private fun getIdentity(): Observable<Identity> {
        Log.d(TAG, "Getting Identity")
        return Discogs.api.getIdentity().umap()
    }
}