package net.treelzebub.umap.api.model

import net.treelzebub.umap.data.DiscogsResponse
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class CollectionFolder(
        val id: Int,
        val count: Int,
        val name: String,
        val resource_url: String
) : DiscogsResponse
