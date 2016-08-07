package net.treelzebub.umap.activity.login

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.umap.Constants
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.DashboardActivity
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.android.toast
import net.treelzebub.umap.auth.AuthService
import net.treelzebub.umap.auth.AuthUtils
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.conduit.LoginConduit
import net.treelzebub.umap.util.android.async

/**
 * Created by Tre Murillo on 5/28/15
 *
 * Provides a one-time login to Discogs.com
 */
class LoginActivity : UmapActivity() {

    private val webView       by bindView<WebView>(R.id.webview)
    private val presenter     by lazy { LoginMvp.Presenter(LoginView()) }

    private val webViewClient = LoginClient()

    private var authUrl: String? = null

    private val login = LoginConduit(this)
        .onComplete { accessToken ->
            if (accessToken == null) {
                toast("Login Failed. Please try again!")
                loadAuthUrl()
            } else {
                TokenHolder.setToken(accessToken)
                AuthUtils.setTokenPrefs(this, accessToken)
                startActivity(DashboardActivity.getIntent(this))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        webView.settings.let {
            it.javaScriptEnabled = true
            it.builtInZoomControls = true
        }
        webView.setWebViewClient(webViewClient)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        loadAuthUrl()
    }

    private fun loadAuthUrl() {
        async({
            val auth = AuthService.instance
            val rt = auth.requestToken
            TokenHolder.setRequestToken(rt)
            authUrl = auth.getAuthorizationUrl(rt) + Constants.DISCOGS_AUTH_URL_APPEND + rt.token
        }, {
            webView.loadUrl(authUrl)
        })
    }

    private inner class LoginClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            if (url?.startsWith(Constants.CALLBACK_URL) ?: false) {
                presenter.getAccessToken(login, url!!)
                return true
            }
            return false
        }
    }
}
