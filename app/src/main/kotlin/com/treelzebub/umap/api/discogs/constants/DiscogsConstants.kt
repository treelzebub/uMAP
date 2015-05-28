package com.treelzebub.umap.api.discogs.constants

/**
 * Created by Tre Murillo on 5/28/15
 * Copyright(c) 2015 Level, Inc.
 */

public val BASE_URL: String = "https://api.discogs.com"
public val REQUEST_TOKEN_URL: String = BASE_URL + "/oauth/request_token"
public val ACCESS_TOKEN_URL: String = BASE_URL + "/oauth/access_token"
public val AUTHORIZATION_URL: String = "https://www.discogs.com/oauth/authorize"
public val AUTH_URL_APPEND: String = "?oauth_token="
public val CALLBACK_URL: String = "oauth://umap.treelzebub.net"

public val CONSUMER_KEY: String = "JfdZojxprrQHOqIpHwpV"
public val CONSUMER_SECRET: String = "uigJkstmlBPlLBFMoxAApEHgIdrCGQsx"
public val SIGNATURE_METHOD: String = "PLAINTEXT"

public val IDENTITY_URL: String = "/oauth/identity"
public val PROFILE_URL: String = "/users" // + /{username}