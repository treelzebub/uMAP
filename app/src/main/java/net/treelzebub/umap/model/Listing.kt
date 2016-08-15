package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 8/14/16
 */
data class Listing(
        val status: String, // "For Sale"
        val price: Price,
        @SerializedName("allow_offers")
        val allowOffers: Boolean,
        @SerializedName("sleeve_condition")
        val sleeveCondition: String,
        val id: String,
        val condition: String,
        val posted: String,
        @SerializedName("ships_from")
        val shipsFrom: String,
        val uri: String,
        val comments: String,
        val seller: Seller,
        val release: ListingRelease,
        @SerializedName("resource_url")
        val resourceUrl: String,
        val audio: Boolean
) : Serializable

data class Price(
        val currency: String,
        val value: String
) : Serializable

data class Seller(
        val username: String,
        @SerializedName("resource_url")
        val resourceUrl: String,
        val id: String
) : Serializable

data class ListingRelease(
        @SerializedName("catalog_number")
        val catalogNumber: String,
        @SerializedName("resource_url")
        val resourceUrl: String,
        val year: String,
        val id: String,
        val description: String
) : Serializable

