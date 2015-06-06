package com.treelzebub.umap.ui

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import butterknife.bindView
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.DiscogsApi
import com.treelzebub.umap.api.discogs.constants.CALLBACK_URL
import com.treelzebub.umap.api.discogs.constants.CONSUMER_KEY
import com.treelzebub.umap.api.discogs.constants.CONSUMER_SECRET
import com.treelzebub.umap.util.TokenHolder
import org.scribe.builder.ServiceBuilder
import org.scribe.model.Verifier
import kotlin.com.treelzebub.umap.util.BusProvider

/**
 * Created by Tre Murillo on 5/28/15
 * Copyright(c) 2015 Level, Inc.
 */
public class DashboardActivity : AppCompatActivity() {

    var prefs: SharedPreferences? = null

    val drawerLayout: DrawerLayout  by bindView(R.id.drawer_layout)
    val navView: NavigationView     by bindView(R.id.navigation_view)
    val content: ViewGroup          by bindView(R.id.content)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.getInstance().register(this)
        setContentView(R.layout.activity_dashboard)
        initToolbar()
        setupDrawerLayout()
        prefs = getSharedPreferences(getString(R.string.key_pref_file), Context.MODE_PRIVATE)

        if (TokenHolder.hasAccessToken(getApplicationContext())) {
            getSupportFragmentManager().beginTransaction().add(R.id.content, HomeFragment()).commit()
        }

        val data = getIntent().getData()
        if (data == null) {
            //            getSupportFragmentManager().beginTransaction().add(R.id.content, LoginFragment()).commit()
        } else {
            requestAccessToken(data)
        }
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupDrawerLayout() {
        navView.setNavigationItemSelectedListener({
            Snackbar.make(content, it.getTitle(), Snackbar.LENGTH_LONG).show()
            it.setChecked(true)
            drawerLayout.closeDrawers()
            true
        })
    }

    private fun requestAccessToken(data: Uri) {
        prefs?.edit()?.putString(getString(R.string.key_oauth_token), data.getQueryParameter("oauth_token"))
        prefs?.edit()?.putString(getString(R.string.key_oauth_verifier), data.getQueryParameter("oauth_verifier"))
        val requestToken = TokenHolder.getRequestToken()
        val verifier = Verifier(data.getQueryParameter("oauth_verifier"))
        val service = ServiceBuilder()
                .apiKey(CONSUMER_KEY)
                .apiSecret(CONSUMER_SECRET)
                .callback(CALLBACK_URL)
                .provider(javaClass<DiscogsApi>())
                .build()
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                val accessToken = service.getAccessToken(requestToken, verifier)
                TokenHolder.setAccessToken(accessToken)
                val editor = prefs?.edit()
                editor?.putString(getString(R.string.key_access_token), accessToken.getToken())
                editor?.putString(getString(R.string.key_access_token_secret), accessToken.getSecret())
                editor?.putString(getString(R.string.key_access_token_raw_response), accessToken.getRawResponse())
                editor?.commit()
                Log.d("OAuth Token: ", accessToken.getToken())
                return null
            }
        }.execute()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
