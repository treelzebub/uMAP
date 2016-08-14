package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 8/14/16
 */
data class MasterRelease(
        val styles: List<String>,
        val genres: List<String>,
        val videos: List<Video>,
        val title: String,
        @SerializedName("main_release")
        val mainRelease: Long,
        @SerializedName("main_release_url")
        val mainReleaseUrl: String, // The URL we just hit.
        val uri: String, // The actual URL of the release.
        val artists: List<Artist>,
        @SerializedName("versions_url")
        val versionsUrl: String, // We'll immediately hit this URL to get individual releases of this master
        val year: Int,
        val images: List<Image>, // Display the one of type "primary"; the rest are images of the media, liner notes, et al.
        @SerializedName("resource_url")
        val resourceUrl: String, // Probably the same as mainReleaseUrl
        val tracklist: List<Track>,
        val id: String,
        @SerializedName("num_for_sale")
        val numForSale: Int,
        @SerializedName("lowest_price")
        val lowestPrice: Float,
        @SerializedName("data_quality")
        val dataQuality: String
) : Serializable
