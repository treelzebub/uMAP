package com.treelzebub.umap.ui

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.app.Fragment
import android.content.Context
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
import org.scribe.builder.ServiceBuilder
import java.lang.ref.WeakReference

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
        val wa = WeakReference<Activity>(getActivity())
        var request_token: String? = null

        object : AsyncTask<WeakReference<Activity>, Void, String>() {
            override fun doInBackground(vararg params: WeakReference<Activity>): String {
                val service = ServiceBuilder()
                        .apiKey(CONSUMER_KEY)
                        .apiSecret(CONSUMER_SECRET)
                        .callback(CALLBACK_URL)
                        .provider(javaClass<DiscogsApi>())
                        .build()
                val rt = service.getRequestToken()
                request_token = rt.getToken()
                return service.getAuthorizationUrl(rt) + AUTH_URL_APPEND + rt.getToken()
            }

            override fun onPostExecute(authurl: String) {
                //unbox WeakReference to
                val prefs = wa.get().getSharedPreferences(getString(R.string.key_pref_file), Context.MODE_PRIVATE)
                prefs.edit().putString(getString(R.string.key_request_token), request_token)
                mWebView.loadUrl(authUrl)
            }
        }.execute(wa)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_oauth, container, false)
    }
}
