package net.treelzebub.umap.api.discogs.model

/**
 * Created by Tre Murillo on 6/6/15
 */
public data class Pagination(
        public var per_page: Int,
        public var pages: Int,
        public var page: Int,
        public var items: Int,
        public var urls: Pair<String, String> // Pair of URLs: Next, Last
)
