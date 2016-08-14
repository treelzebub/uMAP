package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class User(
        val profile: String,
        @SerializedName("wantlist_url")
        val wantlistUrl: String,
        val rank: String,
        @SerializedName("num_pending")
        val numPending: Int,
        val id: Int,
        @SerializedName("num_for_sale")
        val numForSale: Int,
        @SerializedName("home_page")
        val homePage: String,
        val location: String,
        @SerializedName("collection_folders_url")
        val collectionFoldersUrl: String,
        val username: String,
        @SerializedName("collection_fields_url")
        val collectionFieldsUrl: String,
        @SerializedName("releases_contributed")
        val releasesContributed: Int,
        val registered: String,
        @SerializedName("rating_avg")
        val ratingAvg: Float,
        @SerializedName("num_collection")
        val numCollection: Int,
        @SerializedName("releases_rated")
        val releasesRated: Int,
        @SerializedName("num_lists")
        val numLists: Int,
        val name: String,
        @SerializedName("num_wantlist")
        val numWantlist: Int,
        @SerializedName("inventory_url")
        val inventoryUrl: String,
        val uri: String,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("resource_url")
        val resourceUrl: String
) : Serializable
