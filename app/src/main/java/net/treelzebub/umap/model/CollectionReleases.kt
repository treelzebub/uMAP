package net.treelzebub.umap.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class CollectionReleases(
        val pagination: Pagination,
        val releases: List<Release>
) : Serializable
