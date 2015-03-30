package com.treelzebub.umap.api.discogs;

/**
 * Created by Tre Murillo on 3/29/15
 */
public class DiscogsConstants {

    public static final String BASE_URL = "https://api.discogs.com";
    public static final String REQUEST_TOKEN_URL = "/oauth/request_token";
    public static final String ACCESS_TOKEN_URL = "/oauth/access_token";
    public static final String AUTHORIZATION_URL = "/oauth/authorize";
    public static final String CALLBACK_URL = "oauth://treelzebub.umap";

    public static final String CONSUMER_KEY = "JfdZojxprrQHOqIpHwpV";
    public static final String CONSUMER_SECRET = "uigJkstmlBPlLBFMoxAApEHgIdrCGQsx";
    public static final String SIGNATURE_METHOD = "PLAINTEXT";

    public static final String IDENTITY_URL = "/oauth/identity";
    public static final String PROFILE_URL = "/users"; // + /{username}

    private DiscogsConstants() {
    }

}
