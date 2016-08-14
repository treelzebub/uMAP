package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 8/14/16
 */
data class MasterRelease(
        override val styles: List<String>,
        override val genres: List<String>,
        override val videos: List<Video>,
        override val title: String,
        @SerializedName("main_release")
        val mainRelease: Long,
        @SerializedName("main_release_url")
        val mainReleaseUrl: String, // The URL we just hit.
        override val uri: String, // The actual URL of the release.
        override val artists: List<Artist>,
        @SerializedName("versions_url")
        val versionsUrl: String, // We'll immediately hit this URL to get individual releases of this master
        override val year: String,
        override val images: List<Image>, // Display the one of type "primary"; the rest are images of the media, liner notes, et al.
        @SerializedName("resource_url")
        val resourceUrl: String, // Probably the same as mainReleaseUrl
        override val tracklist: List<Track>,
        override val id: String,
        @SerializedName("num_for_sale")
        override val numForSale: String,
        @SerializedName("lowest_price")
        override val lowestPrice: String,
        @SerializedName("data_quality")
        override val dataQuality: String
) : IRelease, Serializable
