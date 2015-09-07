package com.treelzebub.umap.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.squareup.otto.Subscribe
import com.squareup.picasso.Picasso
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.model.User
import com.treelzebub.umap.graphics.CircleTransform
import com.treelzebub.umap.util.BusProvider
import com.treelzebub.umap.util.LoginUtils
import com.treelzebub.umap.util.UserUtils
import com.treelzebub.umap.util.clearPrefs

/**
 * Created by Tre Murillo on 5/28/15
 */
public class DashboardActivity : AppCompatActivity() {

    val drawerLayout: DrawerLayout  by bindView(R.id.drawer_layout)
    val navView: NavigationView     by bindView(R.id.navigation_view)
    val content: ViewGroup          by bindView(R.id.content)
    val avatar: ImageView           by bindView(R.id.avatar)
    val username: TextView          by bindView(R.id.username)
    val name: TextView              by bindView(R.id.name)

    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
        setContentView(R.layout.activity_dashboard)
        setupToolbar()
        setupDrawer()
        val data = getIntent().getData()
        if (data != null) {
            LoginUtils.requestAccessToken(this, data)
        } else {
            UserUtils.syncUser(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.instance.unregister(this)
    }

    private fun setupToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupDrawer() {
        navView.setNavigationItemSelectedListener({
            Snackbar.make(content, it.getTitle(), Snackbar.LENGTH_LONG).show()
            it.setChecked(true)
            when (it.getItemId()) {
            //TODO
            }
            drawerLayout.closeDrawers()
            true
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.clear_prefs -> clearPrefs(this)
        }
        return super.onOptionsItemSelected(item)
    }

    Subscribe
    public fun onUserEvent(event: UserEvent) {
        val user = event.user
        if (user != null) {
            UserUtils.usernameToPrefs(this, user)
            Picasso.with(this).load(user.avatar_url).transform(CircleTransform()).into(avatar)
            username.setText(user.username)
            name.setText(user.name)
        }
    }
}

public class UserEvent(val user: User?)
