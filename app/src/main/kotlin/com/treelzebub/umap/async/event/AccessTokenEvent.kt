package com.treelzebub.umap.async.event

import android.content.Context
import com.treelzebub.umap.R
import com.treelzebub.umap.util.PrefsUtils
import com.treelzebub.umap.util.TokenHolder
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 6/7/15
 */
public class AccessTokenEvent(val c: Context, var accessToken: Token) : Event {

    override fun onSuccess() {
        TokenHolder.setAccessToken(accessToken)
        val editor = PrefsUtils.getPrefs(c)?.edit()
        editor?.putString(c.getString(R.string.key_access_token), accessToken.getToken())
        editor?.putString(c.getString(R.string.key_access_token_secret), accessToken.getSecret())
        editor?.putString(c.getString(R.string.key_access_token_raw_response), accessToken.getRawResponse())
        editor?.commit()
    }

    override fun onFailure() {
    }
}
