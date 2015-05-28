package com.treelzebub.umap.ui

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import butterknife.bindView
import com.treelzebub.umap.R
import kotlin.com.treelzebub.umap.util.BusProvider

/**
 * Created by Tre Murillo on 5/28/15
 * Copyright(c) 2015 Level, Inc.
 */
public class DashboardActivity : Activity() {

    private val listOptions = array("Search", "My Collection", "Accounts")

    private val toolbar: Toolbar? = null
    private var drawerToggle: ActionBarDrawerToggle? = null

    val drawerLayout: DrawerLayout  by bindView(R.id.drawer_layout)
    val listView: ListView          by bindView(R.id.nav_list)
    val authCodeET: EditText        by bindView(R.id.auth_code_et)
    val submitButton: Button        by bindView(R.id.submit_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        BusProvider.getInstance().register(this)
        val prefs: SharedPreferences = this.getSharedPreferences(getString(R.string.key_pref_file), Context.MODE_PRIVATE)
        initDrawer()

        val data = getIntent().getData()
        if (data == null || prefs.getString(getString(R.string.key_oauth_token), "") != "") {
            getFragmentManager().beginTransaction().add(R.id.container, LoginFragment()).commit()
        } else {
            prefs.edit().putString(getString(R.string.key_oauth_token), data.getQueryParameter("oauth_token"))
            prefs.edit().putString(getString(R.string.key_oauth_verifier), data.getQueryParameter("oauth_verifier"))
        }
    }

    private fun initDrawer() {
        val mListAdapter = ArrayAdapter<String>(this@DashboardActivity, android.R.layout.simple_list_item_1, listOptions)
        listView.setAdapter(mListAdapter)
        //TODO toolbar
        drawerToggle = object : ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }
        }
        drawerLayout.setDrawerListener(drawerToggle)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()
        if (id == R.id.action_settings) {
            return true
        }
        if (drawerToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
