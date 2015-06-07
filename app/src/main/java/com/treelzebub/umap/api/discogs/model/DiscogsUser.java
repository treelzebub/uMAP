package com.treelzebub.umap.api.discogs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Tre Murillo on 6/6/15
 */
public class DiscogsUser {

    @Expose
    private String profile;
    @SerializedName("wantlist_url")
    @Expose
    private String wantlistUrl;
    @Expose
    private double rank;
    @SerializedName("num_pending")
    @Expose
    private int numPending;
    @Expose
    private int id;
    @SerializedName("num_for_sale")
    @Expose
    private int numForSale;
    @SerializedName("home_page")
    @Expose
    private String homePage;
    @Expose
    private String location;
    @SerializedName("collection_folders_url")
    @Expose
    private String collectionFoldersUrl;
    @Expose
    private String DiscogsUsername;
    @SerializedName("collection_fields_url")
    @Expose
    private String collectionFieldsUrl;
    @SerializedName("releases_contributed")
    @Expose
    private int releasesContributed;
    @Expose
    private String registered;
    @SerializedName("rating_avg")
    @Expose
    private double ratingAvg;
    @SerializedName("num_collection")
    @Expose
    private int numCollection;
    @SerializedName("releases_rated")
    @Expose
    private int releasesRated;
    @SerializedName("num_lists")
    @Expose
    private int numLists;
    @Expose
    private String name;
    @SerializedName("num_wantlist")
    @Expose
    private int numWantlist;
    @SerializedName("inventory_url")
    @Expose
    private String inventoryUrl;
    @Expose
    private String uri;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("resource_url")
    @Expose
    private String resourceUrl;

    /**
     * @return The profile
     */
    public String getProfile() {
        return profile;
    }

    /**
     * @param profile The profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    public DiscogsUser withProfile(String profile) {
        this.profile = profile;
        return this;
    }

    /**
     * @return The wantlistUrl
     */
    public String getWantlistUrl() {
        return wantlistUrl;
    }

    /**
     * @param wantlistUrl The wantlist_url
     */
    public void setWantlistUrl(String wantlistUrl) {
        this.wantlistUrl = wantlistUrl;
    }

    public DiscogsUser withWantlistUrl(String wantlistUrl) {
        this.wantlistUrl = wantlistUrl;
        return this;
    }

    /**
     * @return The rank
     */
    public double getRank() {
        return rank;
    }

    /**
     * @param rank The rank
     */
    public void setRank(double rank) {
        this.rank = rank;
    }

    public DiscogsUser withRank(double rank) {
        this.rank = rank;
        return this;
    }

    /**
     * @return The numPending
     */
    public int getNumPending() {
        return numPending;
    }

    /**
     * @param numPending The num_pending
     */
    public void setNumPending(int numPending) {
        this.numPending = numPending;
    }

    public DiscogsUser withNumPending(int numPending) {
        this.numPending = numPending;
        return this;
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    public DiscogsUser withId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return The numForSale
     */
    public int getNumForSale() {
        return numForSale;
    }

    /**
     * @param numForSale The num_for_sale
     */
    public void setNumForSale(int numForSale) {
        this.numForSale = numForSale;
    }

    public DiscogsUser withNumForSale(int numForSale) {
        this.numForSale = numForSale;
        return this;
    }

    /**
     * @return The homePage
     */
    public String getHomePage() {
        return homePage;
    }

    /**
     * @param homePage The home_page
     */
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public DiscogsUser withHomePage(String homePage) {
        this.homePage = homePage;
        return this;
    }

    /**
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    public DiscogsUser withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * @return The collectionFoldersUrl
     */
    public String getCollectionFoldersUrl() {
        return collectionFoldersUrl;
    }

    /**
     * @param collectionFoldersUrl The collection_folders_url
     */
    public void setCollectionFoldersUrl(String collectionFoldersUrl) {
        this.collectionFoldersUrl = collectionFoldersUrl;
    }

    public DiscogsUser withCollectionFoldersUrl(String collectionFoldersUrl) {
        this.collectionFoldersUrl = collectionFoldersUrl;
        return this;
    }

    /**
     * @return The DiscogsUsername
     */
    public String getDiscogsUsername() {
        return DiscogsUsername;
    }

    /**
     * @param DiscogsUsername The DiscogsUsername
     */
    public void setDiscogsUsername(String DiscogsUsername) {
        this.DiscogsUsername = DiscogsUsername;
    }

    public DiscogsUser withDiscogsUsername(String DiscogsUsername) {
        this.DiscogsUsername = DiscogsUsername;
        return this;
    }

    /**
     * @return The collectionFieldsUrl
     */
    public String getCollectionFieldsUrl() {
        return collectionFieldsUrl;
    }

    /**
     * @param collectionFieldsUrl The collection_fields_url
     */
    public void setCollectionFieldsUrl(String collectionFieldsUrl) {
        this.collectionFieldsUrl = collectionFieldsUrl;
    }

    public DiscogsUser withCollectionFieldsUrl(String collectionFieldsUrl) {
        this.collectionFieldsUrl = collectionFieldsUrl;
        return this;
    }

    /**
     * @return The releasesContributed
     */
    public int getReleasesContributed() {
        return releasesContributed;
    }

    /**
     * @param releasesContributed The releases_contributed
     */
    public void setReleasesContributed(int releasesContributed) {
        this.releasesContributed = releasesContributed;
    }

    public DiscogsUser withReleasesContributed(int releasesContributed) {
        this.releasesContributed = releasesContributed;
        return this;
    }

    /**
     * @return The registered
     */
    public String getRegistered() {
        return registered;
    }

    /**
     * @param registered The registered
     */
    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public DiscogsUser withRegistered(String registered) {
        this.registered = registered;
        return this;
    }

    /**
     * @return The ratingAvg
     */
    public double getRatingAvg() {
        return ratingAvg;
    }

    /**
     * @param ratingAvg The rating_avg
     */
    public void setRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public DiscogsUser withRatingAvg(double ratingAvg) {
        this.ratingAvg = ratingAvg;
        return this;
    }

    /**
     * @return The numCollection
     */
    public int getNumCollection() {
        return numCollection;
    }

    /**
     * @param numCollection The num_collection
     */
    public void setNumCollection(int numCollection) {
        this.numCollection = numCollection;
    }

    public DiscogsUser withNumCollection(int numCollection) {
        this.numCollection = numCollection;
        return this;
    }

    /**
     * @return The releasesRated
     */
    public int getReleasesRated() {
        return releasesRated;
    }

    /**
     * @param releasesRated The releases_rated
     */
    public void setReleasesRated(int releasesRated) {
        this.releasesRated = releasesRated;
    }

    public DiscogsUser withReleasesRated(int releasesRated) {
        this.releasesRated = releasesRated;
        return this;
    }

    /**
     * @return The numLists
     */
    public int getNumLists() {
        return numLists;
    }

    /**
     * @param numLists The num_lists
     */
    public void setNumLists(int numLists) {
        this.numLists = numLists;
    }

    public DiscogsUser withNumLists(int numLists) {
        this.numLists = numLists;
        return this;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public DiscogsUser withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return The numWantlist
     */
    public int getNumWantlist() {
        return numWantlist;
    }

    /**
     * @param numWantlist The num_wantlist
     */
    public void setNumWantlist(int numWantlist) {
        this.numWantlist = numWantlist;
    }

    public DiscogsUser withNumWantlist(int numWantlist) {
        this.numWantlist = numWantlist;
        return this;
    }

    /**
     * @return The inventoryUrl
     */
    public String getInventoryUrl() {
        return inventoryUrl;
    }

    /**
     * @param inventoryUrl The inventory_url
     */
    public void setInventoryUrl(String inventoryUrl) {
        this.inventoryUrl = inventoryUrl;
    }

    public DiscogsUser withInventoryUrl(String inventoryUrl) {
        this.inventoryUrl = inventoryUrl;
        return this;
    }

    /**
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri The uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    public DiscogsUser withUri(String uri) {
        this.uri = uri;
        return this;
    }

    /**
     * @return The avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl The avatar_url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public DiscogsUser withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    /**
     * @return The resourceUrl
     */
    public String getResourceUrl() {
        return resourceUrl;
    }

    /**
     * @param resourceUrl The resource_url
     */
    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public DiscogsUser withResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }
}
