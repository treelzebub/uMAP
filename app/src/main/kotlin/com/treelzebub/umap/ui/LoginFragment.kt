package com.treelzebub.umap.ui

import android.os.AsyncTask
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import butterknife.bindView
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.DiscogsApi
import com.treelzebub.umap.api.discogs.constants.AUTH_URL_APPEND
import com.treelzebub.umap.api.discogs.constants.CALLBACK_URL
import com.treelzebub.umap.api.discogs.constants.CONSUMER_KEY
import com.treelzebub.umap.api.discogs.constants.CONSUMER_SECRET
import com.treelzebub.umap.util.TokenHolder
import org.scribe.builder.ServiceBuilder

/**
 * Created by Tre Murillo on 5/28/15
 * Copyright(c) 2015 Level, Inc.
 *
 * A fragment that provides a one-time login to Discogs.com
 */
public class LoginFragment : Fragment() {

    private var authUrl: String? = null

    val mWebView: WebView by bindView(R.id.webview)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void): Void? {
                val service = ServiceBuilder()
                        .apiKey(CONSUMER_KEY)
                        .apiSecret(CONSUMER_SECRET)
                        .callback(CALLBACK_URL)
                        .provider(javaClass<DiscogsApi>())
                        .build()
                val rt = service.getRequestToken()
                TokenHolder.setRequestToken(rt)
                authUrl = service.getAuthorizationUrl(rt) + AUTH_URL_APPEND + rt.getToken()
                return null
            }

            override fun onPostExecute(foo: Void?) {
                mWebView.loadUrl(authUrl)
            }
        }.execute()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_oauth, container, false)
    }
}
