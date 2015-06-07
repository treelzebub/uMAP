package com.treelzebub.umap.ui

import android.os.Bundle
import android.support.v4.app.Fragment
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
import kotlin.com.treelzebub.umap.util.BusProvider

/**
 * Created by Tre Murillo on 6/6/15
 */
public class CollectionFragment : Fragment() {

    var collectionReleases: CollectionReleases? = null

    val tempText: TextView by bindView(R.id.collection_item)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.getInstance().register(this)
        syncCollection(getActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_collection, container, false)

        return v
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.getInstance().unregister(this)
    }

    Subscribe
    public fun onCollection(event: CollectionReleasesEvent) {
        collectionReleases = event.collectionReleases
        tempText.setText(event.collectionReleases.releases!!.first().basic_information!!.title)
    }
}
