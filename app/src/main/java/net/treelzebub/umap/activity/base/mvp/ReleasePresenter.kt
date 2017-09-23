package net.treelzebub.umap.activity.base.mvp

import net.treelzebub.umap.model.*

/**
 * Created by Tre Murillo on 8/14/16
 */
open class ReleasePresenter(val view: BaseReleaseView)  {

    fun set(release: IRelease) {
        view.apply {
            release.let {
                cover     = it.images.cover()
                artist    = it.artists.main()
                title     = it.title
//                genre     = R.string.genre_x.str(it.genres.firstOrNull() ?: "")
//                style     = R.string.style_x.str(it.styles.firstOrNull() ?: "")
                year      = "" + it.year
                tracklist = it.tracklist
            }
        }
    }

    fun set(info: BasicInformation) {
        view.apply {
            info.let {
                cover  = it.thumb
                artist = it.artists.main()
                title  = it.title
                genre  = null
                style  = null
                year   = "" + it.year
            }
        }
    }
}