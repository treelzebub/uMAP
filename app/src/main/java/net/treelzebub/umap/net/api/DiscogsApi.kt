package net.treelzebub.umap.net.api

import io.reactivex.Observable
import io.reactivex.Single
import net.treelzebub.umap.model.*
import net.treelzebub.umap.model.Collection
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by treelzebub on 9/22/2017
 */
interface DiscogsApi {

    @GET("/oauth/identity")
    fun getIdentity(): Observable<Identity>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Observable<User>

    @GET("/users/{username}/collection/folders")
    fun getCollection(@Path("username") username: String): Observable<Collection>

    @GET("/users/{username}/collection/folders/{folder_id}")
    fun getCollectionFolder(@Path("username") username: String,
                            @Path("folder_id") folder_id: String): Observable<CollectionFolder>

    @GET("/users/{username}/collection/folders/{folder_id}/releases")
    fun getCollectionReleases(@Path("username") username: String,
                              @Path("folder_id") folder_id: String): Observable<CollectionReleases>

    @GET("/masters/{master_id}")
    fun getMasterRelease(@Path("master_id") masterId: String): Observable<MasterRelease>

    @GET("/releases/{release_id}")
    fun getRelease(@Path("release_id") releaseId: String): Observable<Release>
}