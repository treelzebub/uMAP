package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class BasicInformation(
        var id: Int,
        var title: String,
        var year: Int,
        @SerializedName("resource_url")
        var resourceUrl: String,
        var thumb: String,
        var formats: List<Format>,
        var labels: List<Label>,
        var artists: List<Artist>
) : Serializable
