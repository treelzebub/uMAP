package net.treelzebub.umap.ui

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
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
import com.squareup.picasso.Picasso
import net.treelzebub.umap.R
import net.treelzebub.umap.api.discogs.DiscogsService
import net.treelzebub.umap.graphics.CircleTransform
import net.treelzebub.umap.util.BusProvider
import net.treelzebub.umap.util.clearPrefs

/**
 * Created by Tre Murillo on 5/28/15
 */
public class DashboardActivity : AppCompatActivity() {

    companion object {
        public fun getIntent(c: Context): Intent {
            return Intent(c, DashboardActivity::class.java)
        }
    }

    val drawerLayout: DrawerLayout  by bindView(R.id.drawer_layout)
    val navView: NavigationView     by bindView(R.id.navigation_view)
    val content: ViewGroup          by bindView(R.id.content)
    val avatar: ImageView           by bindView(R.id.avatar)
    val username: TextView          by bindView(R.id.username)
    val name: TextView              by bindView(R.id.name)

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
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupDrawer() {
        setHeader()
        navView.setNavigationItemSelectedListener {
            Snackbar.make(content, it.title, Snackbar.LENGTH_LONG).show()
            it.setChecked(true)
            when (it.itemId) {
            //            TODO
            }
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
            R.id.clear_prefs -> clearPrefs(this)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setHeader() {
        object : AsyncTask<Void, Void, String>() {
            override fun doInBackground(vararg params: Void?): String? {
                return DiscogsService.getIdentity().username
            }

            override fun onPostExecute(result: String?) {
                username.text = result
            }
        }.execute(null)
//        Picasso.with(this).load(DiscogsService.avatar).transform(CircleTransform()).into(avatar)
//        name.text = DiscogsService.getUser()?.name ?: "fail"
    }
}
