package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 8/14/16
 */

private const val PRIMARY = "primary"
//TODO host my own no-cover
private const val NO_COVER = "https://upload.wikimedia.org/wikipedia/commons/b/b9/No_Cover.jpg"

data class Image(
        val height: Int,
        @SerializedName("resource_url")
        val resourceUrl: String, // Do not consume
        val type: String, // We want "primary"
        val uri: String, // Display this.
        val uri150: String,
        val width: Int
) : Serializable

fun Image.isPrimary(): Boolean = type == PRIMARY
fun List<Image>.cover(): String = find { it.isPrimary() }?.uri ?: NO_COVER