package net.treelzebub.umap.api.discogs.model

/**
 * Created by Tre Murillo on 7/4/15
 */
public data class Identity(
        public val id: Int,
        public val username: String,
        public val resource_url: String,
        public val consumer_name: String,

        public val message: String? // error
)
