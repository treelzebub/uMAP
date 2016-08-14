package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.R
import net.treelzebub.umap.inject.ContextInjection
import net.treelzebub.umap.util.android.PrefsUtils
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 10/11/15
 */
object TokenHolder {

    private var requestToken: Token? = null
    var accessToken: Token? = null
        set(token) {
            field = token ?: return
            setTokenPrefs(token)
        }

    fun setToken(token: Token) {
        accessToken = token
        setTokenPrefs(token)
    }

    private fun setTokenPrefs(token: Token) {
        val c = ContextInjection.c
        PrefsUtils.getPrefs(c)?.edit()?.let {
            it.putString(c.getString(R.string.key_access_token), token.token)
            it.putString(c.getString(R.string.key_access_token_secret), token.secret)
            it.commit()
        }
    }

    fun setRequestToken(token: Token) {
        requestToken = token
    }

    fun getRequestToken(): Token {
        return requestToken ?: throw(RuntimeException("Null RequestToken"))
    }

    fun hasAccessToken(): Boolean {
        return accessToken != null || getTokenPrefs()
    }

    fun getTokenPrefs(): Boolean {
        val c = ContextInjection.c
        val prefs = PrefsUtils.getPrefs(c)
        val token = prefs?.getString(c.getString(R.string.key_access_token), null)
        val secret = prefs?.getString(c.getString(R.string.key_access_token_secret), null)
        if (token != null && secret != null) {
            TokenHolder.accessToken = Token(token, secret)
            return true
        } else {
            return false
        }
    }

    fun clearTokens(c: Context) {
        val editor = PrefsUtils.getPrefs(c)?.edit()
        val tokens = listOf(R.string.key_access_token, R.string.key_access_token_secret, R.string.key_access_token_raw_response)
        tokens.forEach {
            editor?.remove(c.getString(it))
        }
        editor?.apply()
        requestToken = null
        accessToken = null
    }
}
