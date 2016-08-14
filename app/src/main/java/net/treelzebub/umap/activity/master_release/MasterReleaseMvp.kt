package net.treelzebub.umap.activity.master_release

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
    override var cover: String
        get() = throw UnsupportedOperationException()
        set(value) {
        }
    override var artist: String
        get() = throw UnsupportedOperationException()
        set(value) {
        }
    override var title: String
        get() = throw UnsupportedOperationException()
        set(value) {
        }
    override var genre: String?
        get() = throw UnsupportedOperationException()
        set(value) {
        }
    override var style: String?
        get() = throw UnsupportedOperationException()
        set(value) {
        }
    override var year: String
        get() = throw UnsupportedOperationException()
        set(value) {
        }
    override var tracklist: List<Track>
        get() = throw UnsupportedOperationException()
        set(value) {
        }
}