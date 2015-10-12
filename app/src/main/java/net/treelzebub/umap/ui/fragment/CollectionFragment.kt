package net.treelzebub.umap.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.squareup.otto.Subscribe
import net.treelzebub.umap.R
import net.treelzebub.umap.api.discogs.DiscogsApi
import net.treelzebub.umap.api.discogs.DiscogsService
import net.treelzebub.umap.api.discogs.model.CollectionReleases
import net.treelzebub.umap.async.event.CollectionReleasesEvent
import net.treelzebub.umap.sync.SyncCenter
import net.treelzebub.umap.ui.adapter.CollectionAdapter
import net.treelzebub.umap.util.BusProvider
import net.treelzebub.umap.util.async

/**
 * Created by Tre Murillo on 6/6/15
 */
public class CollectionFragment : Fragment() {

    var collectionReleases: CollectionReleases? = null

    private val tempText: TextView         by bindView(R.id.collection_item)
    private val recyclerView: RecyclerView by bindView(R.id.recycler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
        syncCollectionReleases()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.instance.unregister(this)
    }

    private fun syncCollectionReleases() {
        async({
            DiscogsService.getCollectionReleases()
        }, {
            tempText.text = it.releases.first().basic_information.title
            recyclerView.adapter = CollectionAdapter(activity, it)
        })
    }
}
