package net.treelzebub.umap.activity

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
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.squareup.picasso.Picasso
import net.treelzebub.umap.R
import net.treelzebub.umap.graphics.CircleTransform
import net.treelzebub.umap.sync.SyncCenter
import net.treelzebub.umap.ui.fragment.CollectionFragment
import net.treelzebub.umap.util.bus.BusProvider
import net.treelzebub.umap.util.android.PrefsUtils
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by Tre Murillo on 5/28/15
 */
class DashboardActivity : UmapActivity() {

    companion object {
        fun getIntent(c: Context): Intent {
            return Intent(c, DashboardActivity::class.java)
        }
    }

    val drawerLayout: DrawerLayout  by bindView(R.id.drawer_layout)
    val navView: NavigationView     by bindView(R.id.navigation_view)
    val content: ViewGroup          by bindView(R.id.content)
    val avatar: ImageView           by bindView(R.id.avatar)
    val username: TextView          by bindView(R.id.username)
    val name: TextView              by bindView(R.id.name)

    private var drawerToggle: ActionBarDrawerToggle? = null

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
        setContentView(R.layout.activity_dashboard)
        setupToolbar()
        setupDrawer()
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.instance.unregister(this)
    }

    private fun setupToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)
        drawerLayout.setDrawerListener(drawerToggle)
        drawerToggle?.syncState()
    }

    private fun setupDrawer() {
        setHeader()
        navView.setNavigationItemSelectedListener {
            it.setChecked(true)
            val ft = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.collection -> ft.add(R.id.content, CollectionFragment())
            }
            ft.commit()
            drawerLayout.closeDrawers()
            true
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

    private fun setHeader() {
        val user = SyncCenter.deserializeUser(this) ?: return
        username.text = user.username
        Picasso.with(this).load(user.avatar_url).transform(CircleTransform()).into(avatar)
        name.text = user.name
    }
}
