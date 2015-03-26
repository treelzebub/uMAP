package com.treelzebub.umap.auth;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Tre Murillo on 3/23/15
 */
public interface LoginService {

    @POST("/request_token")
    @FormUrlEncoded
    UserCode getRequestToken();
//    OAuth oauth_consumer_key="your_consumer_key",
//            oauth_nonce="random_string_or_timestamp",
//            oauth_signature="your_consumer_secret&",
//            oauth_signature_method="PLAINTEXT",
//            oauth_timestamp="current_timestamp",
//            oauth_callback="your_callback"



    @POST("/token")
    AccessToken getAccessToken(@Query("code") String code,
                               @Query("grant_type") String grantType);
}
