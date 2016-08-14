package net.treelzebub.umap.activity.base.mvp

import net.treelzebub.umap.model.Track

/**
 * Created by Tre Murillo on 8/14/16
 */
interface BaseReleaseView {
    var cover: String
    var artist: String
    var title: String
    var genre: String?
    var style: String?
    var year: String
    var tracklist: List<Track>
}