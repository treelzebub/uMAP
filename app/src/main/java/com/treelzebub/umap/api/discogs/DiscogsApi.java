package com.treelzebub.umap.api.discogs;

import retrofit.http.GET;

/**
 * Created by Tre Murillo 1/27/2015
 */
public interface DiscogsApi {

    @GET("/request_token")
    String getAuthUrl();

}
