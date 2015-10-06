package net.treelzebub.umap.api.discogs.model

/**
 * Created by Tre Murillo on 6/6/15
 */
public data class Pagination {
    public var per_page: Int = -1
    public var pages: Int = -1
    public var page: Int = -1
    public var items: Int = -1
    public var urls: Pair<String, String>? = null // Pair of URLs: Next, Last
}