package net.treelzebub.umap.activity.master_release

import com.levelmoney.klarity.withString
import net.treelzebub.umap.R
import net.treelzebub.umap.model.MasterRelease
import net.treelzebub.umap.model.Track
import net.treelzebub.umap.model.cover
import net.treelzebub.umap.model.main

/**
 * Created by Tre Murillo on 8/14/16
 */
object MasterReleaseMvp {

    class Presenter(val view: View) {

        fun set(master: MasterRelease) {
            view.apply {
                master.let {
                    cover     = it.images.cover()
                    artist    = it.artists.main()
                    title     = it.title
                    genre     = it.genres.firstOrNull()
                    style     = it.styles.firstOrNull()
                    year      = "" + it.year
                    tracklist = it.tracklist
                }
            }
        }
    }

    interface View {
        var cover: String
        var artist: String
        var title: String
        var genre: String?
        var style: String?
        var year: String
        var tracklist: List<Track>
    }
}

class MasterReleaseView(val a: MasterReleaseActivity) : MasterReleaseMvp.View {
    override var cover: String          by a.withString(R.id.cover).notNull
    override var artist: String         by a.withString(R.id.artist).notNull
    override var title: String          by a.withString(R.id.title).notNull
    override var genre: String?         by a.withString(R.id.genre)
    override var style: String?         by a.withString(R.id.style)
    override var year: String           by a.withString(R.id.year).notNull
    override var tracklist: List<Track> = listOf() //TODO recycler property?
}