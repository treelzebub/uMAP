package net.treelzebub.umap.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by Tre Murillo on 8/14/16
 */
data class CollectionRelease(
        val id: String,
        @SerializedName("instance_id")
        val instanceId: Int,
        @SerializedName("folder_id")
        val folderId: Int,
        val rating: Float,
        @SerializedName("basic_information")
        val info: BasicInformation,
        val notes: List<Note>
) : Serializable
