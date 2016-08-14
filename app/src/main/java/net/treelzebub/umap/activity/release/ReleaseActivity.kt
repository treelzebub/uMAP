package net.treelzebub.umap.activity.release

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.base.BaseReleaseActivity
import net.treelzebub.umap.activity.base.adapter.TracklistAdapter
import net.treelzebub.umap.activity.base.mvp.ReleasePresenter
import net.treelzebub.umap.activity.base.mvp.ReleaseView
import net.treelzebub.umap.activity.collection.CollectionActivity
import net.treelzebub.umap.conduit.onSuccess
import net.treelzebub.umap.data.Data
import net.treelzebub.umap.util.android.withIntent
import org.jetbrains.anko.find

/**
 * Created by Tre Murillo on 6/18/16
 */
class ReleaseActivity : BaseReleaseActivity() {

    companion object {
        fun get(c: Context, release: String): Intent {
            return Intent(c, ReleaseActivity::class.java).putExtra("release_id", release)
        }
    }

    private val releaseId: String? by withIntent { it.getStringExtra("release_id") }

    private val presenter by lazy { ReleasePresenter(ReleaseView(this)) }
    override val adapter  = TracklistAdapter()

    private val conduit = ReleaseConduit(this)
        .onSuccess {
            presenter.set(it!!)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_release) //TODO different layout
        setSupportActionBar(find<Toolbar>(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val lastRelease = Data.lastRelease.peek()
        val release = releaseId ?: lastRelease?.id
        if (release == null) {
            goHome()
        } else {
            conduit.load(release)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goHome() {
        startActivity(Intent(this, CollectionActivity::class.java))
    }
}
