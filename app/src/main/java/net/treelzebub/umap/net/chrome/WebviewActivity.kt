package net.treelzebub.umap.net.chrome

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.webkit.WebView
import android.webkit.WebViewClient
import net.treelzebub.umap.R
import net.treelzebub.umap.ui.activity.UmapActivity

/**
 * Created by Tre Murillo on 6/18/16
 *
 * This Activity is used as a fallback when there is no browser installed that supports
 * Chrome Custom Tabs
 */
abstract class WebviewActivity : UmapActivity() {

    abstract var webViewClient: WebViewClient

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val url = intent.getStringExtra(EXTRA_URL)
        val webView = findViewById(R.id.webview) as WebView?
        webView!!.setWebViewClient(webViewClient)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar?)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        webView.loadUrl(url)
    }

    companion object {

        protected val EXTRA_URL = "extra.url"
    }
}