package com.treelzebub.umap.api.discogs.model

/**
 * Created by Tre Murillo on 6/6/15
 */
public data class Release {
    public var id: Int = -1
    public var instance_id: Int = -1
    public var folder_id: Int = -1
    public var rating: Int = -1
    public var basic_information: BasicInformation? = null
    public var notes: List<Note>? = null
}