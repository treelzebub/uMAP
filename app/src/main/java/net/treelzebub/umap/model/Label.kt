package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Label(
        @SerializedName("resource_url")
        val resourceUrl: String,
        @SerializedName("entity_type")
        val entityType: String,
        val catno: String,
        val id: Int,
        val name: String
) : Serializable
