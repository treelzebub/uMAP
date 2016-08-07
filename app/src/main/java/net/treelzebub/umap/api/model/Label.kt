package net.treelzebub.umap.api.model

import net.treelzebub.umap.data.DiscogsResponse
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Label(
        val resource_url: String,
        val entity_type: String,
        val catno: String,
        val id: Int,
        val name: String
) : DiscogsResponse
