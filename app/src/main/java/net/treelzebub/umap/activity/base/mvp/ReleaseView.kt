package net.treelzebub.umap.activity.base.mvp

import android.widget.ImageView
import com.levelmoney.klarity.withString
import com.squareup.picasso.Picasso
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.base.BaseReleaseActivity
import net.treelzebub.umap.model.Track
import org.jetbrains.anko.find

/**
 * Created by Tre Murillo on 8/14/16
 */
class ReleaseView(val a: BaseReleaseActivity) : BaseReleaseView {

    override var artist: String         by a.withString(R.id.artist).notNull
    override var title: String          by a.withString(R.id.title).notNull
    override var genre: String?         by a.withString(R.id.genre)
    override var style: String?         by a.withString(R.id.style)
    override var year: String           by a.withString(R.id.year).notNull

    override var tracklist: List<Track> = listOf()
        set(list) {
            field = list
            a.adapter.tracklist = list
        }

    private val coverIV by lazy { a.find<ImageView>(R.id.cover) }
    override var cover: String = ""
        set(url) {
            field = url
            Picasso.with(a)
                   .load(url)
                   .fit()
                   .centerCrop()
                   .placeholder(R.drawable.icon)
                   .into(coverIV)
        }
}