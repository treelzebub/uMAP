package net.treelzebub.umap.api.model

import net.treelzebub.umap.data.DiscogsResponse
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class CollectionReleases(
        val pagination: Pagination,
        val  releases: List<Release>
) : DiscogsResponse
