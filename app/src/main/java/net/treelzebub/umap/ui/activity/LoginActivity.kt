package net.treelzebub.umap.ui.activity

import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.umap.Constants
import net.treelzebub.umap.R
import net.treelzebub.umap.auth.AuthService
import net.treelzebub.umap.auth.LoginUtils
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.net.chrome.WebviewActivity
import net.treelzebub.umap.util.async

/**
 * Created by Tre Murillo on 5/28/15
 *
 * Provides a one-time login to Discogs.com
 */
class LoginActivity : WebviewActivity() {

    override var webViewClient: WebViewClient = RequestTokenCallback()

    private val webView by bindView<WebView>(R.id.webview)

    private var authUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadAuthUrl()
        webView.settings.apply {
            builtInZoomControls = true
            javaScriptEnabled = true
        }
        webView.setWebViewClient(webViewClient)
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

    private inner class RequestTokenCallback : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            if (url != null && url.startsWith(Constants.CALLBACK_URL)) {
                //TODO spinner visible
                LoginUtils.requestAccessToken(this@LoginActivity, Uri.parse(url))
                return true
            }
            return false
        }
    }
}

