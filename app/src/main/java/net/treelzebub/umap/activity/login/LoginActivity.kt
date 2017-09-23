package net.treelzebub.umap.activity.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_login.*
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.api.login.Login
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

/**
 * Created by Tre Murillo on 5/28/15
 *
 * Provides a one-time login to Discogs.com
 */
class LoginActivity : UmapActivity() {

    private val webViewClient = LoginClient()

    private lateinit var login: Login

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = Login(this)

        webview.let {
            it.settings.builtInZoomControls = true
            it.settings.javaScriptEnabled   = true
            it.webViewClient = webViewClient
        }
        loadAuthUrl()
    }

    private fun loadAuthUrl() {
        doAsync {
            val authUrl = login.getAuthUrl()
            uiThread {
                webview.loadUrl(authUrl)
            }
        }
    }

    private inner class LoginClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            if (url != null && Discogs.CALLBACK_URL in url) {
                toast("Validating Identity...")
                doAsync {
                    login.processUser(url)
                    login.initUser()
                }
                return true
            }
            return false
        }
    }
}