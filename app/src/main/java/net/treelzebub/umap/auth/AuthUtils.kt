package net.treelzebub.umap.auth

import android.content.Context
import android.net.Uri
import net.treelzebub.umap.R
import net.treelzebub.umap.util.PrefsUtils
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 10/11/15
 * Copyright(c) 2015 Level, Inc.
 */
object AuthUtils {

    fun setTokenPrefs(c: Context, token: Token) {
        val editor = PrefsUtils.getPrefs(c)?.edit()
        editor?.putString(c.getString(R.string.key_access_token), token.token)
        editor?.putString(c.getString(R.string.key_access_token_secret), token.secret)
        editor?.commit()
    }

    fun getTokenPrefs(c: Context): Boolean {
        val prefs = PrefsUtils.getPrefs(c)
        val token = prefs?.getString(c.getString(R.string.key_access_token), null)
        val secret = prefs?.getString(c.getString(R.string.key_access_token_secret), null)
        if (token != null && secret != null) {
            TokenHolder.setToken(Token(token, secret))
            return true
        } else {
            return false
        }
    }
}
