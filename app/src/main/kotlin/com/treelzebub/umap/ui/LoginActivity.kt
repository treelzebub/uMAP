package com.treelzebub.umap.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import com.treelzebub.umap
import com.treelzebub.umap.DISCOGS_AUTH_URL_APPEND
import com.treelzebub.umap.R
import com.treelzebub.umap.auth.AuthService
import com.treelzebub.umap.auth.TokenHolder
import com.treelzebub.umap.util.BusProvider

/**
 * Created by Tre Murillo on 5/28/15
 *
 * Provides a one-time login to Discogs.com
 */
public class LoginActivity : AppCompatActivity() {

    private var authUrl: String? = null

    val webView: WebView by bindView(R.id.webview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
        setContentView(R.layout.activity_login)
        loadAuthUrl()
        val wvSettings = webView.getSettings()
        wvSettings.setBuiltInZoomControls(true)
        wvSettings.setJavaScriptEnabled(true)
        webView.setWebViewClient(RequestTokenCallback(this))
    }

    private fun loadAuthUrl() {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void): Void? {
                val sAuthService = AuthService.instance
                val rt = sAuthService.getRequestToken()
                TokenHolder.requestToken = rt
                authUrl = sAuthService.getAuthorizationUrl(rt) + DISCOGS_AUTH_URL_APPEND + rt.getToken()
                return null
            }

            override fun onPostExecute(result: Void?) {
                webView.loadUrl(authUrl)
            }
        }.execute()
    }

    private inner class RequestTokenCallback(val c: Context) : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            if (url != null && url.startsWith(umap.CALLBACK_URL)) {
                val i = Intent(c, javaClass<DashboardActivity>()).setData(Uri.parse(url))
                startActivity(i)
                return true
            }
            return false
        }
    }
}
