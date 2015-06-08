package com.treelzebub.umap.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.model.CollectionFolder
import butterknife.bindView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.treelzebub.umap.api.discogs.model.CollectionReleases

/**
 * Created by Tre Murillo on 6/7/15
 */
public class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    var context: Context? = null
    var collectionReleases = CollectionReleases()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        context = parent.getContext()
        val v = LayoutInflater.from(context).inflate(R.layout.collection_recycler, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val albumCoverUrl = collectionReleases.releases!!.get(i).basic_information!!.thumb
        Picasso.with(context)
                .load(albumCoverUrl)
                .resize(50, 50)
                .centerCrop()
                .into(holder.albumCover)
        holder.artist.setText(collectionReleases.releases!!.get(i).basic_information!!.artists.first().name)
        holder.title.setText(collectionReleases.releases!!.get(i).basic_information!!.title)
    }

    override fun getItemCount(): Int {
        return collectionReleases.releases!!.size()
    }

    private inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val albumCover: ImageView  by bindView(R.id.cardview_album_cover)
        val artist: TextView       by bindView(R.id.cardview_artist)
        val title: TextView        by bindView(R.id.cardview_title)
    }
}
