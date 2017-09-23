package net.treelzebub.umap.activity.base

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.activity.base.adapter.TracklistAdapter
import net.treelzebub.umap.util.android.withIntent
import org.jetbrains.anko.find

/**
 * Created by Tre Murillo on 8/14/16
 */
abstract class BaseReleaseActivity : UmapActivity() {

    companion object {
        const val KEY_RELEASE_ID = "release_id"
    }

    abstract val adapter: TracklistAdapter

    protected val releaseId by withIntent { it.getStringExtra(KEY_RELEASE_ID) }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        find<RecyclerView>(R.id.recycler).let {
            it.layoutManager = LinearLayoutManager(this)
            it.itemAnimator = DefaultItemAnimator()
            it.adapter = adapter
        }
    }
}
