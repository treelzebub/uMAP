package net.treelzebub.umap.activity.collection

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_collection.*
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.conduit.onSuccess
import net.treelzebub.umap.data.Data
import net.treelzebub.umap.ui.adapter.CollectionAdapter
import net.treelzebub.umap.util.android.UmapVersions
import net.treelzebub.umap.util.android.subscribeToBismarck

/**
 * Created by Tre Murillo on 8/7/16
 */
class CollectionActivity : UmapActivity() {

    private val adapter = CollectionAdapter(this)

    private val collection = CollectionConduit(this)
        .onSuccess {
            Data.collection.insert(it)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection)
        setupToolbar()
        setupDrawer()

        recycler.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(this)
            it.itemAnimator  = DefaultItemAnimator()
            it.adapter       = adapter
        }

        subscribeToBismarck(Data.collection) {
            it?.releases?.let { adapter.releases = it }
            if (UmapVersions.isDebug()) {
                Log.d("Collection Contents", GsonBuilder().setPrettyPrinting().create().toJson(it))
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        collection.load()
    }
}
