package net.treelzebub.umap.activity.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_dashboard.*
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setupToolbar()
        setupDrawer()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawer_layout.openDrawer(GravityCompat.START)
            R.id.clear_prefs -> PrefsUtils.clearPrefs(this)
        }
        return super.onOptionsItemSelected(item)
    }
}
