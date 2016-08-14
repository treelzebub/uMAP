package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
class Release(
        override val title: String,
        override val id: String,
        override val artists: List<Artist>,
        @SerializedName("data_quality")
        override val dataQuality: String,
        val thumb: String,
        val community: Community,
        val companies: List<Company>,
        val country: String,
        @SerializedName("date_added")
        val dateAdded: String, //TODO try LocalDate
        @SerializedName("date_changed")
        val dateChanged: String,
        @SerializedName("estimated_weight")
        val estimatedWeight: String,
        @SerializedName("extraartists")
        val extraArtists: List<Artist>,
        @SerializedName("format_quantity")
        val formatQuantity: Int,
        val formats: List<Format>,
        override val genres: List<String>,
        val identifiers: List<Any>, // Pair: type, value
        override val images: List<Image>,
        val lavels: List<Label>,
        @SerializedName("lowest_price")
        override val lowestPrice: Float,
        @SerializedName("master_id")
        val masterId: String,
        @SerializedName("master_url")
        val masterUrl: String,
        val notes: String, // split using /n delimiter
        @SerializedName("num_for_sale")
        override val numForSale: Int,
        val released: String, // Might be a year, might be a date in format 09 June 2002
        @SerializedName("released_formatted")
        val releasedFormatted: Int, // year
        @SerializedName("release_url")
        val releaseUrl: String,
        val series: Array<String>,
        val status: String,
        override val styles: List<String>,
        override val tracklist: List<Track>,
        override val uri: String,
        override val videos: List<Video>,
        override val year: String
) : IRelease, Serializable
