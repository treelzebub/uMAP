package net.treelzebub.umap.api.discogs

import net.treelzebub.umap.api.discogs.model.*
import net.treelzebub.umap.api.discogs.model.Collection
import retrofit.http.GET
import retrofit.http.Path

/**
 * Created by Tre Murillo on 6/6/15
 */

public interface DiscogsApi {

    @GET("/oauth/identity")
    public fun getIdentity(): Identity

    @GET("/users/{username}")
    public fun getUser(@Path("username") username: String): User

    @GET("/users/{username}/collection/folders")
    public fun getCollection(@Path("username") username: String): Collection

    @GET("/users/{username}/collection/folders/{folder_id}")
    public fun getCollectionFolder(@Path("username") username: String,
                                   @Path("folder_id") folder_id: String): CollectionFolder

    @GET("/users/{username}/collection/folders/{folder_id}/releases")
    public fun getCollectionReleases(@Path("username") username: String,
                                     @Path("folder_id") folder_id: String): CollectionReleases
}