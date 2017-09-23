package net.treelzebub.umap.activity.base.mvp

import android.content.Context
import net.treelzebub.umap.R
import net.treelzebub.umap.model.BasicInformation
import net.treelzebub.umap.model.IRelease
import net.treelzebub.umap.model.cover
import net.treelzebub.umap.model.main

/**
 * Created by Tre Murillo on 8/14/16
 */
open class ReleasePresenter(
        private val context: Context,
        private val view: BaseReleaseView
)  {

    fun set(release: IRelease) {
        view.apply {
            release.let {
                cover     = it.images.cover()
                artist    = it.artists.main()
                title     = it.title
                genre     = it.genres.firstOrNull()?.let {
                    context.getString(R.string.genre_x, it)
                }
                style     = it.styles.firstOrNull()?.let {
                    context.getString(R.string.style_x, it)
                }
                year      = it.year
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