package com.treelzebub.umap.api.discogs.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

/**
 * Created by Tre Murillo on 6/6/15
 * Copyright(c) 2-115 Level, Inc.
 */
public data class User(
    private var profile: String? = null,
    private var wantlist_url: String? = null,
    private var rank: Int = -1,
    private var num_pending: Int = -1,
    private var id: Int = -1,
    private var num_for_sale: Int = -1,
    private var home_page: String? = null,
    private var location: String? = null,
    private var collection_folders_url: String? = null,
    private var username: String? = null,
    private var collection_fields_url: String? = null,
    private var releases_contributed: Int = -1,
    private var registered: DateTime? = null, // ex: "2012-08-15T21:13:36"
    private var rating_avg: Double = -1.0, // hundredths
    private var num_collection: Int = -1,
    private var releases_rated: Int = -1,
    private var num_lists: Int = -1,
    private var name: String? = null,
    private var num_wantlist: Int = -1,
    private var inventory_url: String? = null,
    private var avatar_url: String? = null,
    private var uri: String? = null,
    private var resourceUrl: String? = null
) {
    public fun getName(): String? {
        return username
    }
}

