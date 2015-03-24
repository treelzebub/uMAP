package com.treelzebub.umap.api.discogs;

import com.treelzebub.umap.api.discogs.model.Record;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.POST;

/**
 * Created by Tre Murillo 1/27/2015
 */

//  Consumer Key	    JfdZojxprrQHOqIpHwpV
//  Consumer Secret	    uigJkstmlBPlLBFMoxAApEHgIdrCGQsx
//  Request Token URL	http://api.discogs.com/oauth/request_token
//  Authorize URL	    http://www.discogs.com/oauth/authorize
//  Access Token URL	http://api.discogs.com/oauth/access_token

public interface DiscogsApi {

    @POST(" ")
    Record getRecord(String title, Callback<Response> callback);

}
