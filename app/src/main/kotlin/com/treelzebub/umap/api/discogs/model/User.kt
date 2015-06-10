package com.treelzebub.umap.api.discogs.model

import java.io.Serializable

/**
 * Created by Tre Murillo on 6/6/15
 */
public data class User : Serializable {
    public var profile: String? = null
    public var wantlist_url: String? = null
    public var rank: Double = -1.0
    public var num_pending: Int = -1
    public var id: Int = -1
    public var num_for_sale: Int = -1
    public var home_page: String? = null
    public var location: String? = null
    public var collection_folders_url: String? = null
    public var username: String? = null
    public var collection_fields_url: String? = null
    public var releases_contributed: Int = -1
    public var registered: String? = null
    public var rating_avg: Double = -1.0
    public var num_collection: Int = -1
    public var releases_rated: Int = -1
    public var num_lists: Int = -1
    public var name: String? = null
    public var num_wantlist: Int = -1
    public var inventory_url: String? = null
    public var uri: String? = null
    public var avatar_url: String? = null
    public var resource_url: String? = null
}
