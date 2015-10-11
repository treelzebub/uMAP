package net.treelzebub.umap.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import net.treelzebub.umap.Constants
import net.treelzebub.umap.R
import net.treelzebub.umap.auth.AuthService
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.util.BusProvider
import net.treelzebub.umap.util.async

/**
 * Created by Tre Murillo on 5/28/15
 *
 * Provides a one-time login to Discogs.com
 */
public class LoginActivity : AppCompatActivity() {

    val webView: WebView by bindView(R.id.webview)

    private var authUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
        setContentView(R.layout.activity_login)
        loadAuthUrl()
        val wvSettings = webView.settings
        wvSettings.builtInZoomControls = true
        wvSettings.javaScriptEnabled = true
        webView.setWebViewClient(RequestTokenCallback())
    }

    private fun loadAuthUrl() {
        async({
            val sAuthService = AuthService.instance
            val rt = sAuthService.requestToken
            TokenHolder.requestToken = rt
            authUrl = sAuthService.getAuthorizationUrl(rt) + Constants.DISCOGS_AUTH_URL_APPEND + rt.token
        }, {
            webView.loadUrl(authUrl)
        })
    }

    private inner class RequestTokenCallback() : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            if (url != null && url.startsWith(Constants.CALLBACK_URL)) {
                val i = Intent(this@LoginActivity, DashboardActivity::class.java).setData(Uri.parse(url))
                startActivity(i)
                return true
            }
            return false
        }
    }
}
