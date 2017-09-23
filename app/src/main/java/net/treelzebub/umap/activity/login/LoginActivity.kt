package net.treelzebub.umap.activity.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_login.*
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.MainActivity
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.auth.Users
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.login.Login
import net.treelzebub.umap.util.rx.umap
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
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

    private fun login(url: String) {
        doAsync {
            login.complete(url).subscribe {
                Discogs.api.getUser(it.username).umap().subscribe {
                    Users.setUser(this@LoginActivity, it)
                    next()
                }
            }
        }
    }

    private fun next() {
        startActivity<MainActivity>()
        finish()
    }

    private inner class LoginClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return if (Discogs.CALLBACK_URL in url) {
                login(url); true
            } else false
        }
    }
}