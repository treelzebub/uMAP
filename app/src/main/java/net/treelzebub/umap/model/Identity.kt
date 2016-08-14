package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 7/4/15
 */
data class Identity(
        val id: Int,
        val username: String,
        @SerializedName("resource_url")
        val resourceUrl: String,
        @SerializedName("consumer_name")
        val consumerName: String,
        val message: String? // error
) : Serializable
