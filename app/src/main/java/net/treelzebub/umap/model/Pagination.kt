package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Pagination(
        @SerializedName("per_page")
        var perPage: Int,
        var pages: Int,
        var page: Int,
        var items: Int,
        var urls: Pair<String, String> // Pair of URLs: Next, Last
) : Serializable
