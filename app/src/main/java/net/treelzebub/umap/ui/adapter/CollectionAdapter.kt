package net.treelzebub.umap.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotterknife.bindView
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.release.ReleaseActivity
import net.treelzebub.umap.model.CollectionRelease

/**
 * Created by Tre Murillo on 6/7/15
 */
class CollectionAdapter(private val c: Context) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    var releases: List<CollectionRelease> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(c).inflate(R.layout.card_release, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val info = releases[i].info
        Picasso.with(c)
               .load(info.thumb)
               .fit()
               .centerCrop()
               .placeholder(ContextCompat.getDrawable(c, R.drawable.icon))
               .into(holder.albumCover)
        holder.artist.text = info.artists.first().name
        holder.title.text  = info.title
        holder.label.text  = info.labels.first().name
        holder.year.text   = "${info.year}"
        holder.clicker.setOnClickListener {
            c.startActivity(ReleaseActivity.get(c, info.id.toString()))
        }
    }

    override fun getItemCount() = releases.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val clicker: View          by bindView(R.id.clicker)
        val albumCover: ImageView  by bindView(R.id.cover)
        val artist: TextView       by bindView(R.id.artist)
        val title: TextView        by bindView(R.id.title)
        val label: TextView        by bindView(R.id.label)
        val year: TextView         by bindView(R.id.year)
    }
}
