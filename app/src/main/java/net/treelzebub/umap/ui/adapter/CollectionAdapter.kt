package net.treelzebub.umap.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.squareup.picasso.Picasso
import net.treelzebub.umap.R
import net.treelzebub.umap.api.model.CollectionReleases

/**
 * Created by Tre Murillo on 6/7/15
 */
class CollectionAdapter(val c: Context, val releases: CollectionReleases) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(c).inflate(R.layout.collection_card, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val info = releases.releases.get(i).basic_information
        var albumCoverUrl = info.thumb
        if (albumCoverUrl.isNullOrEmpty()) {
            albumCoverUrl = "https://upload.wikimedia.org/wikipedia/commons/b/b9/No_Cover.jpg" // "http://treelzebub.net/img/no-cover.png"
        }
        Picasso.with(c)
                .load(albumCoverUrl)
                .fit()
                .centerCrop()
                .into(holder.albumCover)
        holder.artist.text = info.artists.first().name
        holder.title.text = info.title
        holder.label.text = info.labels.first().name
        holder.year.text = "${info.year}"
    }

    override fun getItemCount(): Int {
        return releases.releases.size
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val albumCover: ImageView  by bindView(R.id.cover)
        val artist: TextView       by bindView(R.id.artist)
        val title: TextView        by bindView(R.id.title)
        val label: TextView        by bindView(R.id.label)
        val year: TextView         by bindView(R.id.year)
    }
}
