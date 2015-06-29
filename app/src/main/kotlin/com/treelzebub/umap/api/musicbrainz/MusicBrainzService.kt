package com.treelzebub.umap.api.musicbrainz

import retrofit.http.GET
import retrofit.http.Path

/**
 * Created by Tre Murillo on 6/28/15
 *
 * Let's use 3 different fucking servers to access cover art, because Discogs allows only 100 requests per day.
 *
 * https://musicbrainz.org/doc/Cover_Art_Archive/API
 */
public interface MusicBrainzService {

    // This returns a list of all possible results in the db
    GET("/recording/?query=artist:{artist-name}+recording:{release}")
    public fun getMbid(Path("artist-name") artist: String,
                       Path("release") release: String)

    GET("/release/{mbid}")
    public fun getCoverArt(Path("mbid") mbid: String): String

}
