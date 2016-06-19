package net.treelzebub.umap.api.discogs.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class User(
        val profile: String,
        val wantlist_url: String,
        val rank: Double,
        val num_pending: Int,
        val id: Int,
        val num_for_sale: Int,
        val home_page: String,
        val location: String,
        val collection_folders_url: String,
        val username: String,
        val collection_fields_url: String,
        val releases_contributed: Int,
        val registered: String,
        val rating_avg: Double,
        val num_collection: Int,
        val releases_rated: Int,
        val num_lists: Int,
        val name: String,
        val num_wantlist: Int,
        val inventory_url: String,
        val uri: String,
        val avatar_url: String,
        val resource_url: String
) : Serializable
