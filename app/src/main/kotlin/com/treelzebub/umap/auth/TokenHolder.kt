package com.treelzebub.umap.auth

import android.content.Context
import com.treelzebub.umap.R
import org.scribe.model.Token
import kotlin.platform.platformStatic

/**
 * Created by tremurillo on 6/9/15
 */
public object TokenHolder {

    public var requestToken: Token? = null
    public var accessToken: Token? = null

    platformStatic
    public fun createTokenFromPref(c: Context): Boolean {
        if (accessToken != null) return true
        val prefs = c.getApplicationContext().getSharedPreferences(c.getString(R.string.key_pref_file), Context.MODE_PRIVATE)
        val key = prefs.getString(c.getString(R.string.key_access_token), null)
        val secret = prefs.getString(c.getString(R.string.key_access_token_secret), null)
        val rawResponse = prefs.getString(c.getString(R.string.key_access_token_raw_response), null)
        if (key == null || secret == null || rawResponse == null) {
            return false
        }
        accessToken = Token(key, secret, rawResponse)
        return true
    }

    platformStatic
    public fun hasAccessToken(c: Context): Boolean {
        return createTokenFromPref(c)
    }

    platformStatic
    public fun clearTokens() {
        requestToken = null
        accessToken = null
    }
}
