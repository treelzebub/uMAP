package net.treelzebub.umap.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 7/4/15
 */
data class Identity(
        val id: Int,
        val username: String,
        val resource_url: String,
        val consumer_name: String,

        val message: String? // error
) : Serializable
