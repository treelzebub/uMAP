package net.treelzebub.umap.api.model

import net.treelzebub.umap.data.DiscogsResponse
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Note(
        val field_id: Int,
        val value: String // Liner Note content
) : DiscogsResponse
