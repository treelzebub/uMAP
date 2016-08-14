package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 8/14/16
 */
data class Track(
        val duration: String, // "4:45"
        val position: String, // May match "1" or "A"
        @SerializedName("type_")
        val type: String, // Looks like this is always "track"...
        val title: String,
        @SerializedName("extraartists")
        val extraArtists: List<Artist>?
) : Serializable
