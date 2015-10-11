package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.R
import net.treelzebub.umap.util.getPrefs
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 10/11/15
 * Copyright(c) 2015 Level, Inc.
 */
public object TokenHolder {


    public var requestToken: Token? = null
    public var accessToken: Token? = null

    public fun hasAccessToken(): Boolean {
        return accessToken != null
    }

    public fun getOauthTokenFromPref(c: Context): String? {
        return getPrefs(c)?.getString(c.getString(R.string.key_oauth_token), null)
    }

    public fun clearTokens(c: Context) {
        val editor = getPrefs(c)?.edit()
        val tokens = listOf(R.string.key_access_token, R.string.key_access_token_secret, R.string.key_access_token_raw_response)
        tokens.forEach {
            editor?.remove(c.getString(it))
        }
        requestToken = null
        accessToken = null
    }

    private fun createTokenFromPref(c: Context): Boolean {
        if (accessToken != null) return true
        val prefs = getPrefs(c)
        val key = prefs?.getString(c.getString(R.string.key_access_token), null)
        val secret = prefs?.getString(c.getString(R.string.key_access_token_secret), null)
        val rawResponse = prefs?.getString(c.getString(R.string.key_access_token_raw_response), null)
        if (key == null || secret == null || rawResponse == null) {
            return false
        }
        accessToken = Token(key, secret, rawResponse)
        return true
    }
}
