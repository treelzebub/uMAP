package net.treelzebub.umap.api.discogs.model

/**
 * Created by Tre Murillo on 6/6/15
 */
public data class Format {
    public var qty: Int = -1
    public var descriptions: List<String> = listOf() // Mini, EP,
    public var name: String? = null // CD, LP, Cassette, etc
}