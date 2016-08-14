package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Artist(
        val id: Long,
        val name: String,
        val join: String,
        @SerializedName("resource_url")
        val resourceUrl: String,
        val anv: String,
        val tracks: String,
        val role: String
) : Serializable

fun List<Artist>.main(): String = firstOrNull()?.name ?: "No Artist"