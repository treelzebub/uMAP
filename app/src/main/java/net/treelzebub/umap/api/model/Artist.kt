package net.treelzebub.umap.api.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Artist(
        val id: Int,
        val name: String,
        val join: String,
        val resource_url: String,
        val anv: String,
        val tracks: String,
        val role: String
) : Serializable
