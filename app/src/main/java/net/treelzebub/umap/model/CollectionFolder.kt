package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class CollectionFolder(
        val id: Int,
        val count: Int,
        val name: String,
        @SerializedName("resource_url")
        val resourceUrl: String
) : Serializable
