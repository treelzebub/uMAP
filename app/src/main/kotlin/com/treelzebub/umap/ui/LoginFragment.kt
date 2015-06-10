package com.treelzebub.umap.ui

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import butterknife.bindView
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.constants.AUTH_URL_APPEND
import com.treelzebub.umap.api.discogs.constants.CALLBACK_URL
import com.treelzebub.umap.api.discogs.constants.CONSUMER_KEY
import com.treelzebub.umap.api.discogs.constants.CONSUMER_SECRET
import com.treelzebub.umap.auth.DiscogsApi
import com.treelzebub.umap.util.BusProvider
import com.treelzebub.umap.util.TokenHolder
import com.treelzebub.umap.util.UserUtils
import org.scribe.builder.ServiceBuilder

/**
 * Created by Tre Murillo on 5/28/15
 *
 * A fragment that provides a one-time login to Discogs.com
 */
public class LoginFragment : Fragment() {

    companion object {
        private val TAG = javaClass<LoginFragment>().getSimpleName()
    }

    private var authUrl: String? = null

    val webView: WebView by bindView(R.id.webview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
        loadAuthUrl()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.getSettings().setBuiltInZoomControls(true)
        webView.getSettings().setJavaScriptEnabled(true)
        webView.setWebViewClient(Callback())
    }

    private fun loadAuthUrl() {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void): Void? {
                val oAuthService = ServiceBuilder()
                        .apiKey(CONSUMER_KEY)
                        .apiSecret(CONSUMER_SECRET)
                        .callback(CALLBACK_URL)
                        .provider(javaClass<DiscogsApi>())
                        .build()
                val rt = oAuthService.getRequestToken()
                TokenHolder.setRequestToken(rt)
                authUrl = oAuthService.getAuthorizationUrl(rt) + AUTH_URL_APPEND + rt.getToken()
                return null
            }

            override fun onPostExecute(result: Void?) {
                webView.loadUrl(authUrl)
            }
        }.execute()
    }

    private inner class Callback : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
            if (url != null && url.startsWith(CALLBACK_URL)) {
                val i = Intent(getActivity(), javaClass<DashboardActivity>()).setData(Uri.parse(url))
                startActivity(i)
                return true
            }
            return false
        }
    }
}
