package com.treelzebub.umap.endpoints; /**
 * Created by Tre Murillo 1/27/2015
 */

import com.treelzebub.umap.discogs.Record;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

public interface Api {
    public static final String USER_AGENT = "User-Agent: uMAP/0.01 retrofit/1.9.0";
    public static final String ACCEPT_JSON = "Accept: application/vnd.github.v3.full+json";

    @Headers({ACCEPT_JSON, USER_AGENT})
    @GET("/masters/{master_id}")
    public Record getRecord(@Query("q") String master_id, Callback<Response> callback);


}
