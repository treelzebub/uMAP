package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
data class Note(
        @SerializedName("field_id")
        val fieldId: Int,
        val value: String // Liner Note content
) : Serializable
