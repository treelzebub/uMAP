package net.treelzebub.umap

/**
 * Created by Tre Murillo on 5/28/15
 */
object Constants {

    val USER_AGENT: String = "Android uMAP/0.001"
    val CALLBACK_URL: String = "oauth://umap.treelzebub.net"

    val ENCODING_TYPE: String = "UTF-8"
    val CONTENT_FORM_URLENCODED: String = "application/x-www-form-urlencoded"

    val DISCOGS_BASE_URL: String = "https://api.discogs.com"
    val DISCOGS_REQUEST_TOKEN_URL: String = DISCOGS_BASE_URL + "/oauth/request_token"
    val DISCOGS_ACCESS_TOKEN_URL: String = DISCOGS_BASE_URL + "/oauth/access_token"
    val DISCOGS_AUTHORIZATION_URL: String = "https://www.discogs.com/oauth/authorize"
    val DISCOGS_AUTH_URL_APPEND: String = "?oauth_token="

    val CREDENTIALS_STORE_FILENAME: String = "umap.prefs"
    val USER_FILENAME: String = "umap.discogs.user"
}