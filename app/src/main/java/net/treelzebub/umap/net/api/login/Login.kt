package net.treelzebub.umap.net.api.login

import android.content.Context
import android.net.Uri
import android.util.Log
import net.treelzebub.umap.R
import net.treelzebub.umap.auth.user.Users
import net.treelzebub.umap.model.Identity
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.api.TokenService
import net.treelzebub.umap.util.TAG
import org.scribe.model.Token
import org.scribe.model.Verifier
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer

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

    fun processUser(url: String) {
        val token = getAccessToken(url)
        initRetrofit(token)
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
        val consumer = OkHttpOAuthConsumer(key, secret)
        Discogs.init(consumer, token)
    }

    fun initUser() {
        Log.d(TAG, "Getting Identity")
        Discogs.api.getIdentity().doOnNext{
            setUser(it)
        }.doOnError {
            Log.d(TAG, "Get Identity Failed")
        }
    }

    private fun setUser(identity: Identity) {
        Log.d(TAG, "Setting User")
        Discogs.api.getUser(identity.username).doOnNext {
            Users.set(context, it)
        }.doOnError {
            Log.d(TAG, "Error setting user")
        }
    }
}