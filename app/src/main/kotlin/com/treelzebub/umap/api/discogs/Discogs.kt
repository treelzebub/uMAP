package com.treelzebub.umap.api.discogs

import com.treelzebub.umap.api.discogs.model.User
import com.treelzebub.umap.api.discogs.model.Collection
import retrofit.client.Response
import retrofit.http.GET
import retrofit.http.Path

/**
 * Created by Tre Murillo on 6/6/15
 */

public interface Discogs {

    GET("/users/{user}")
    public fun getUser(Path("user") user: String): User

    GET("/users/{user}/collection/folders")
    public fun getCollection(Path("user") user: String): Collection
}