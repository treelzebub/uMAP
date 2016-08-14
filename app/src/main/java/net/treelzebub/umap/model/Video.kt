package net.treelzebub.umap.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 8/14/16
 */
data class Video(
        val duration: Long,
        val description: String,
        val embed: Boolean?,
        val uri: String,
        val title: String
) : Serializable
