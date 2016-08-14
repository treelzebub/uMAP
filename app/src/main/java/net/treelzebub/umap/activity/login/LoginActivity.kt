package net.treelzebub.umap.activity.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.umap.Constants
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.activity.collection.CollectionActivity
import net.treelzebub.umap.conduit.onSuccess
import net.treelzebub.umap.conduit.withSpinner
import net.treelzebub.umap.data.Data
import net.treelzebub.umap.util.android.toast
import net.treelzebub.umap.util.kotlin.TAG

/**
 * Created by Tre Murillo on 5/28/15
 *
 * Provides a one-time login to Discogs.com
 */
class LoginActivity : UmapActivity() {

    private val webView       by bindView<WebView>(R.id.webview)
    private val presenter     by lazy { LoginMvp.Presenter(LoginView()) }

    private val webViewClient = LoginClient()

    private val requestToken = RequestTokenConduit(this)
        .withSpinner()
        .onComplete { authUrl ->
            if (authUrl == null) {
                Log.d(TAG, "Loading auth url failed. Retrying...")
                loadAuthUrl()
            } else {
                Log.d(TAG, "Auth url succeeded. Loading...")
                webView.loadUrl(authUrl)
            }
        }

    private val login = AccessTokenConduit(this)
        .withSpinner()
        .onComplete { accessToken ->
            if (accessToken == null) {
                toast("Login Failed. Please try again!")
                Log.d(TAG, "Failed getting Access Token. Reloading auth url.")
                loadAuthUrl()
            } else {
                Log.d(TAG, "Got Access Token. Hitting Identity endpoint...")
                identity.load(null)
            }
        }

    private val identity = IdentityConduit(this)
        .withSpinner()
        .onSuccess { identity ->
            if (identity == null) {
                Log.d(TAG, "Identity was null. Reloading auth url.")
                loadAuthUrl()
            } else {
                Log.d(TAG, "Got Identity. Hitting User endpoint.")
                user.load(Bundle().apply { putString("username", identity.username) })
            }
        }

    private val user = UserConduit(this)
        .withSpinner()
        .onSuccess { user ->
            if (user == null) {
                Log.d(TAG, "User was null. Reloading auth url.")
                loadAuthUrl()
            } else {
                Log.d(TAG, "Got User. Inserting into bismarck and going to dash...")
                Data.user.insert(user)
                toast("Login successful!")
                startActivity(Intent(this, CollectionActivity::class.java))
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        webView.let {
            it.settings.builtInZoomControls = true
            it.settings.javaScriptEnabled   = true
            it.setWebViewClient(webViewClient)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        loadAuthUrl()
    }

    private fun loadAuthUrl() {
        presenter.loadAuthUrl(requestToken)
    }

    private inner class LoginClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            if (url != null && Constants.CALLBACK_URL in url) {
                presenter.getAccessToken(login, url)
                return true
            }
            return false
        }
    }
}
