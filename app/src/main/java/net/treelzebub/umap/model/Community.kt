package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Tre Murillo on 8/14/16
 */
data class Community(val contributors: List<Contributor>)

data class Contributor(
        @SerializedName("resource_url")
        val resourceUrl: String,
        val username: String
)

data class Submitter(
        @SerializedName("resource_url")
        val resourceUrl: String,
        val username: String
)

data class Company(
        val catno: String,
        @SerializedName("entity_type")
        val entityType: String,
        @SerializedName("entity_type_name")
        val entityTypeName: String,
        val id: String,
        val name: String,
        @SerializedName("resource_url")
        val resourceUrl: String
)