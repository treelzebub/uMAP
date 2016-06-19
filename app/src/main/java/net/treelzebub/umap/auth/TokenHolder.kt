package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.R
import net.treelzebub.umap.util.PrefsUtils
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 10/11/15
 */
object TokenHolder {

    private var requestToken: Token? = null
    private var accessToken: Token? = null

    fun getToken(): Token? {
        return accessToken
    }

    fun setToken(token: Token) {
        accessToken = token
    }

    fun setRequestToken(token: Token) {
        requestToken = token
    }

    fun getRequestToken(): Token {
        return requestToken ?: throw(RuntimeException("Null RequestToken"))
    }

    fun clearTokens(c: Context) {
        val editor = PrefsUtils.getPrefs(c)?.edit()
        val tokens = listOf(R.string.key_access_token, R.string.key_access_token_secret, R.string.key_access_token_raw_response)
        tokens.forEach {
            editor?.remove(c.getString(it))
        }
        requestToken = null
        accessToken = null
    }
}
