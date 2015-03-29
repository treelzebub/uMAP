package com.treelzebub.umap.api.discogs;

/**
 * Created by Tre Murillo on 3/29/15
 */
public class DiscogsConstants {
    public static final String BASE_URL = "https://api.discogs.com/oauth";
    public static final String REQUEST_TOKEN_URL = "/request_token";
    public static final String ACCESS_TOKEN_URL = "/access_token";
    public static final String AUTHORIZATION_URL = "/authorize";
    public static final String CALLBACK_URL = "oauth://treelzebub.umap";

    public static final String CONSUMER_KEY = "JfdZojxprrQHOqIpHwpV";
    public static final String CONSUMER_SECRET = "uigJkstmlBPlLBFMoxAApEHgIdrCGQsx";

    public static final String SIGNATURE_METHOD = "PLAINTEXT";

    private DiscogsConstants() {
    }
}
