package net.treelzebub.umap.activity.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.squareup.picasso.Picasso
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.activity.collection.CollectionActivity
import net.treelzebub.umap.android.subscribeToBismarck
import net.treelzebub.umap.api.model.User
import net.treelzebub.umap.data.Data
import net.treelzebub.umap.graphics.CircleTransform
import net.treelzebub.umap.util.android.PrefsUtils

/**
 * Created by Tre Murillo on 5/28/15
 */
class DashboardActivity : UmapActivity() {

    companion object {
        fun getIntent(c: Context): Intent {
            return Intent(c, DashboardActivity::class.java)
        }
    }

    private val drawerLayout: DrawerLayout  by bindView(R.id.drawer_layout)
    private val navView: NavigationView     by bindView(R.id.navigation_view)
    private val toolbar: Toolbar            by bindView(R.id.toolbar)
    private val avatar: ImageView           by bindView(R.id.avatar)
    private val username: TextView          by bindView(R.id.username)
    private val name: TextView              by bindView(R.id.name)

    private val drawerToggle: ActionBarDrawerToggle
            by lazy { ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setupToolbar()
        setupDrawer()

        subscribeToBismarck(Data.user) {
            setUser(it)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupDrawer() {
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            drawerLayout.closeDrawers()
            startActivity(Intent(this, CollectionActivity::class.java))
            true
        }
    }

    private fun setUser(user: User?) {
        user?.let {
            username.text = it.username
            name.text     = it.name
            Picasso.with(this)
                   .load(it.avatar_url)
                   .transform(CircleTransform())
                   .into(avatar)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.clear_prefs -> PrefsUtils.clearPrefs(this)
        }
        return super.onOptionsItemSelected(item)
    }
}
