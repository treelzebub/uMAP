package net.treelzebub.umap.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_release.view.*
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.base.BaseReleaseActivity
import net.treelzebub.umap.activity.master.MasterReleaseActivity
import net.treelzebub.umap.model.CollectionRelease
import net.treelzebub.umap.util.android.inflater
import org.jetbrains.anko.startActivity

/**
 * Created by Tre Murillo on 6/7/15
 */
class CollectionAdapter(private val context: Context) : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    var releases: List<CollectionRelease> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(parent.inflater.inflate(R.layout.card_release, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val info = releases[i].info
        Picasso.with(context)
               .load(info.thumb)
               .fit()
               .centerCrop()
               .placeholder(ContextCompat.getDrawable(context, R.drawable.icon))
               .into(holder.albumCover)
        holder.artist.text = info.artists.first().name
        holder.title.text  = info.title
        holder.label.text  = info.labels.first().name
        holder.year.text   = "${info.year}"
        holder.clicker.setOnClickListener {
            context.startActivity<MasterReleaseActivity>(BaseReleaseActivity.KEY_RELEASE_ID to info.id.toString())
        }
    }

    override fun getItemCount() = releases.size

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val clicker    = v.clicker
        val albumCover = v.cover
        val artist     = v.artist
        val title      = v.title
        val label      = v.label
        val year       = v.year
    }
}
