package com.treelzebub.umap.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import com.squareup.otto.Subscribe
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.constants.CALLBACK_URL
import com.treelzebub.umap.async.event.LoginEvent
import com.treelzebub.umap.async.login

/**
 * Created by Tre Murillo on 5/28/15
 *
 * A fragment that provides a one-time login to Discogs.com
 */
public class LoginFragment : Fragment() {

    companion object {
        private val TAG = javaClass<LoginFragment>().getSimpleName()
    }

    val webView: WebView by bindView(R.id.webview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_login, container, false)
        webView.getSettings().setBuiltInZoomControls(true)
        webView.getSettings().setJavaScriptEnabled(true)
        webView.setWebViewClient(Callback())
        return v
    }

    private inner class Callback : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (url != null && url.startsWith(CALLBACK_URL)) {
                val i = Intent(getActivity(), javaClass<DashboardActivity>()).setData(Uri.parse(url))
                startActivity(i)
                return true
            }
            return false
        }
    }

    Subscribe
    public fun onLoginEvent(event: LoginEvent) {
        webView.loadUrl(event.authUrl)
    }
}
