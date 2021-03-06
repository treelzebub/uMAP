package net.treelzebub.umap.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Format(
        val qty: Int,
        val descriptions: List<String>, // Mini, EP,
        val name: String // CD, LP, Cassette, etc
) : Serializable
