package net.treelzebub.umap.api.discogs.model

/**
 * Created by Tre Murillo on 6/6/15
 */
public data class BasicInformation {
    public var id: Int = -1
    public var title: String? = null
    public var year: Int = -1
    public var resource_url: String? = null
    public var thumb: String? = null
    public var formats: List<Format> = listOf()
    public var labels: List<Label> = listOf()
    public var artists: List<Artist> = listOf()
}