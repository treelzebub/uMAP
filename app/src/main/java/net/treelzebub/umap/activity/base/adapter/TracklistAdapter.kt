package net.treelzebub.umap.activity.base.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotterknife.bindView
import net.treelzebub.umap.R
import net.treelzebub.umap.model.Track
import net.treelzebub.umap.util.android.inflater

/**
 * Created by Tre Murillo on 8/14/16
 */
class TracklistAdapter : RecyclerView.Adapter<TracklistAdapter.Holder>() {

    var tracklist: List<Track> = listOf()
        set(list) {
            field = list
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(parent.inflater.inflate(R.layout.item_track, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.populate(tracklist[position])
    }

    override fun getItemCount() = tracklist.size

    class Holder(v: View) : RecyclerView.ViewHolder(v) {
        private val position  by bindView<TextView>(R.id.position)
        private val title     by bindView<TextView>(R.id.title)
        private val duration  by bindView<TextView>(R.id.duration)

        fun populate(track: Track) {
            position.text = track.position
            title.text    = track.title
            duration.text = track.duration
        }
    }
}
