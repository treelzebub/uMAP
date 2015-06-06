package com.treelzebub.umap.api.discogs.model;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

/**
 * Created by Tre Murillo on 6/6/15
 */

public class User {
    @SerializedName("profile")
    private String profile;

    @SerializedName("wantlist_url")
    private String wantlistUrl;

    @SerializedName("rank")
    private int rank;

    @SerializedName("num_pending")
    private
    private int numPending;

    @SerializedName("id")
    private int id;

    @SerializedName("num_for_sale")
    private int numForSale;

    @SerializedName("home_page")
    private String homePage;

    @SerializedName("location")
    private String location;

    @SerializedName("collection_folders_url")
    private String collectionFoldersUrl;

    @SerializedName("username")
    private String username;

    @SerializedName("collection_fields_url")
    private String collectionFieldsUrl;

    @SerializedName("releases_contributed")
    private int releasesContributed;

    @SerializedName("registered")
    private DateTime registered; // ex "2012-08-15T211336"

    @SerializedName("rating_avg")
    private double ratingAvg; // hundredths

    @SerializedName("num_collection")
    private int numCollection;

    @SerializedName("releases_rated")
    private int releasesRated;

    @SerializedName("num_lists")
    private int numLists;

    @SerializedName("name")
    private String name;

    @SerializedName("num_wantlist")
    private int numWantlist;

    @SerializedName("inventory_url")
    private String inventoryUrl;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("uri")
    private String uri;

    @SerializedName("resource_url")
    private String resourceUrl;
}

