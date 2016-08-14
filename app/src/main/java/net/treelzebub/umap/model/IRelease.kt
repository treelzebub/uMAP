package net.treelzebub.umap.model

/**
 * Created by Tre Murillo on 8/14/16
 */
interface IRelease {
    val styles: List<String>
    val genres: List<String>
    val videos: List<Video>
    val title: String
    val id: String
    val uri: String
    val artists: List<Artist>
    val year: String
    val images: List<Image>
    val tracklist: List<Track>
    val numForSale: Int
    val lowestPrice: Float
    val dataQuality: String
}
