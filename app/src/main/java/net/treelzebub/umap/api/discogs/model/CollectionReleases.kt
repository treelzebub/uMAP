package net.treelzebub.umap.api.discogs.model

/**
 * Created by Tre Murillo on 6/6/15
 */
public data class CollectionReleases(
        val pagination: Pagination,
        val  releases: List<Release>
)
