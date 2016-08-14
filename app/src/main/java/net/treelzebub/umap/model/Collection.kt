package net.treelzebub.umap.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Collection(
        val folders: List<CollectionFolder>
) : Serializable
