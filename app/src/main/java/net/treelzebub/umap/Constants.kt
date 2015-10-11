package net.treelzebub.umap

/**
 * Created by Tre Murillo on 5/28/15
 */
public class Constants {
    companion object {
        public val USER_AGENT: String = "Android uMAP/0.001"
        public val CALLBACK_URL: String = "oauth://umap.treelzebub.net"

        public val ENCODING_TYPE: String = "UTF-8"
        public val CONTENT_FORM_URLENCODED: String = "application/x-www-form-urlencoded"

        public val DISCOGS_BASE_URL: String = "https://api.discogs.com"
        public val DISCOGS_REQUEST_TOKEN_URL: String = DISCOGS_BASE_URL + "/oauth/request_token"
        public val DISCOGS_ACCESS_TOKEN_URL: String = DISCOGS_BASE_URL + "/oauth/access_token"
        public val DISCOGS_AUTHORIZATION_URL: String = "https://www.discogs.com/oauth/authorize"
        public val DISCOGS_AUTH_URL_APPEND: String = "?oauth_token="
        public val DISCOGS_IDENTITY_URL: String = "/oauth/identity"
        public val DISCOGS_PROFILE_URL: String = "/users" // + /{username}

        public val DISCOGS_CONSUMER_KEY: String = "JfdZojxprrQHOqIpHwpV"
        public val DISCOGS_CONSUMER_SECRET: String = "uigJkstmlBPlLBFMoxAApEHgIdrCGQsx"

        public val CREDENTIALS_STORE_FILENAME: String = "umap.prefs"
        public val USER_FILENAME: String = "umap.discogs.user"
    }

    private constructor()
}