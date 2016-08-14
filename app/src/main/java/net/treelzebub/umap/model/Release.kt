package net.treelzebub.umap.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
class Release(
        val id: String,
        val instance_id: Int,
        val folder_id: Int,
        val rating: Int,
        val basic_information: BasicInformation,
        val notes: List<Note>
) : Serializable
