package com.treelzebub.umap.api.discogs.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

/**
 * Created by Tre Murillo on 6/6/15
 * Copyright(c) 2-115 Level, Inc.
 */
public class User {

    Expose
    public var profile: String? = null

    SerializedName("wantlist_url")
    Expose
    public var wantlistUrl: String? = null

    Expose
    public var rank: Double = -1.0

    SerializedName("num_pending")
    Expose
    public var numPending: Int = -1

    Expose
    public var id: Int = -1

    SerializedName("num_for_sale")
    Expose
    public var numForSale: Int = -1

    SerializedName("home_page")
    Expose
    public var homePage: String? = null

    Expose
    public var location: String? = null

    SerializedName("collection_folders_url")
    Expose
    public var collectionFoldersUrl: String? = null

    Expose
    public var username: String? = null
    SerializedName("collection_fields_url")

    Expose
    public var collectionFieldsUrl: String? = null
    SerializedName("releases_contributed")

    Expose
    public var releasesContributed: Int = -1

    Expose
    public var registered: String? = null
    SerializedName("rating_avg")

    Expose
    public var ratingAvg: Double = -1.0
    SerializedName("num_collection")

    Expose
    public var numCollection: Int = -1
    SerializedName("releases_rated")

    Expose
    public var releasesRated: Int = -1
    SerializedName("num_lists")

    Expose
    public var numLists: Int = -1

    Expose
    public var name: String? = null

    SerializedName("num_wantlist")
    Expose
    public var numWantlist: Int = -1

    SerializedName("inventory_url")
    Expose
    public var inventoryUrl: String? = null

    Expose
    public var uri: String? = null
    SerializedName("avatar_url")

    Expose
    public var avatarUrl: String? = null
    SerializedName("resource_url")

    Expose
    public var resourceUrl: String? = null
}
