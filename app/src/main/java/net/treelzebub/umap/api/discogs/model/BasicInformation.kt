package net.treelzebub.umap.api.discogs.model

/**
 * Created by Tre Murillo on 6/6/15
 */
public data class BasicInformation(
        public var id: Int,
        public var title: String,
        public var year: Int,
        public var resource_url: String,
        public var thumb: String,
        public var formats: List<Format>,
        public var labels: List<Label>,
        public var artists: List<Artist>
)
