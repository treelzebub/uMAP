package net.treelzebub.umap.activity.login

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.umap.Constants
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.dashboard.DashboardActivity
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.util.android.toast
import net.treelzebub.umap.data.Data

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
        .onComplete { authUrl ->
            if (authUrl == null) {
                loadAuthUrl()
            } else {
                webView.loadUrl(authUrl)
            }
        }

    private val login = AccessTokenConduit(this)
        .onComplete { accessToken ->
            if (accessToken == null) {
                toast("Login Failed. Please try again!")
                loadAuthUrl()
            } else {
                identity.load(null)
            }
        }

    private val identity = IdentityConduit(this)
        .onComplete { identity ->
            if (identity == null) {
                loadAuthUrl()
            } else {
                user.load(Bundle().apply { putString("username", identity.username) })
            }
        }

    private val user = UserConduit(this)
        .onComplete { user ->
            if (user == null) {
                loadAuthUrl()
            } else {
                Data.user.insert(user)
                toast("Login successful!")
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
