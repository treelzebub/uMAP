package net.treelzebub.umap.activity.base

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.activity.base.adapter.TracklistAdapter
import org.jetbrains.anko.find

/**
 * Created by Tre Murillo on 8/14/16
 */
abstract class BaseReleaseActivity : UmapActivity() {

    abstract val adapter: TracklistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        find<RecyclerView>(R.id.recycler).let {
            it.layoutManager = LinearLayoutManager(this)
            it.itemAnimator  = DefaultItemAnimator()
            it.adapter       = adapter
        }
    }
}
