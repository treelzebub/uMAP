package com.treelzebub.umap.ui

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
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.model.CollectionReleases
import com.treelzebub.umap.async.event.CollectionReleasesEvent
import com.treelzebub.umap.async.syncCollection
import com.treelzebub.umap.ui.adapter.CollectionAdapter
import kotlin.com.treelzebub.umap.util.BusProvider

/**
 * Created by Tre Murillo on 6/6/15
 */
public class CollectionFragment : Fragment() {

    var collectionReleases: CollectionReleases? = null

    val tempText: TextView by bindView(R.id.collection_item)
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.getInstance().register(this)
        syncCollection(getActivity(), true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_collection, container, false)
        recyclerView = v.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView?.setHasFixedSize(true)
        recyclerView?.setLayoutManager(LinearLayoutManager(getActivity()))
        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.getInstance().unregister(this)
    }

    Subscribe
    public fun onCollectionReleases(event: CollectionReleasesEvent) {
        collectionReleases = event.collectionReleases
        tempText.setText(event.collectionReleases.releases?.first()?.basic_information?.title)
        recyclerView?.setAdapter(CollectionAdapter(event.collectionReleases))
        recyclerView?.getAdapter()?.notifyDataSetChanged()
    }
}
