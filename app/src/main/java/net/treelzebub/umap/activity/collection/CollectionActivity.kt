package net.treelzebub.umap.activity.collection

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_collection.*
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.auth.Users
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.api.DiscogsApi
import net.treelzebub.umap.ui.adapter.CollectionAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Tre Murillo on 8/7/16
 */
class CollectionActivity : UmapActivity() {

    private val adapter = CollectionAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        setupToolbar()
        setupDrawer()

        recycler.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this)
            it.itemAnimator = DefaultItemAnimator()
            it.adapter = adapter
        }

        doAsync {
            Discogs.api.getCollectionReleases(Users.user!!.username, DiscogsApi.ID_ROOT_COLLECTION_FOLDER).subscribe {
                data ->
                uiThread { adapter.releases = data.releases }
            }
        }
    }
}