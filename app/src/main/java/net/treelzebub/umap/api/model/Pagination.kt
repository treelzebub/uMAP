package net.treelzebub.umap.api.model

import net.treelzebub.umap.data.DiscogsResponse
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Pagination(
        var per_page: Int,
        var pages: Int,
        var page: Int,
        var items: Int,
        var urls: Pair<String, String> // Pair of URLs: Next, Last
) : DiscogsResponse
