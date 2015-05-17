package com.treelzebub.umap.api.discogs;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by Tre Murillo 1/27/2015
 */
public interface DiscogsApi {

    @GET(DiscogsConstants.REQUEST_TOKEN_URL)
    Response getRequestToken();

    @POST(DiscogsConstants.ACCESS_TOKEN_URL)
    Discogs.TokenResponse getAccessToken();

//    @GET(DiscogsConstants.IDENTITY_URL)
//    Identity getIdentity();
//
//    @GET(DiscogsConstants.PROFILE_URL)
//    Profile getProfile();

    //...TODO
}
