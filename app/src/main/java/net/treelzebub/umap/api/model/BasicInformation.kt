package net.treelzebub.umap.api.model

/**
 * Created by Tre Murillo on 6/6/15
 */
data class BasicInformation(
        var id: Int,
        var title: String,
        var year: Int,
        var resource_url: String,
        var thumb: String,
        var formats: List<Format>,
        var labels: List<Label>,
        var artists: List<Artist>
)
