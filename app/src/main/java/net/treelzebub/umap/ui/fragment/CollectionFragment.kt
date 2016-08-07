package net.treelzebub.umap.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView
import net.treelzebub.umap.R
import net.treelzebub.umap.api.DiscogsService
import net.treelzebub.umap.ui.adapter.CollectionAdapter
import net.treelzebub.umap.util.android.async
import net.treelzebub.umap.util.bus.BusProvider

/**
 * Created by Tre Murillo on 6/6/15
 */
class CollectionFragment : Fragment() {

    private val recyclerView: RecyclerView by bindView(R.id.recycler)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
        syncCollectionReleases()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
            // TODO spinner
            DiscogsService.getCollectionReleases()
        }, {
            // TODO spinner
            recyclerView.adapter = CollectionAdapter(activity, it)
        })
    }
}
