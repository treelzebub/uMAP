package net.treelzebub.umap.api

import net.treelzebub.umap.api.model.*
import net.treelzebub.umap.api.model.Collection
import retrofit.http.GET
import retrofit.http.Path

/**
 * Created by Tre Murillo on 6/6/15
 */

interface DiscogsApi {

    @GET("/oauth/identity")
    fun getIdentity(): Identity

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): User

    @GET("/users/{username}/collection/folders")
    fun getCollection(@Path("username") username: String): Collection

    @GET("/users/{username}/collection/folders/{folder_id}")
    fun getCollectionFolder(@Path("username") username: String,
                            @Path("folder_id") folder_id: String): CollectionFolder

    @GET("/users/{username}/collection/folders/{folder_id}/releases")
    fun getCollectionReleases(@Path("username") username: String,
                              @Path("folder_id") folder_id: String): CollectionReleases

    @GET("/releases/{release_id}")
    fun getRelease(@Path("release_id") releaseId: String): Release
}