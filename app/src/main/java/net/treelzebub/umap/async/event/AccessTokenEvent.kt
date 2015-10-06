package net.treelzebub.umap.async.event

import android.content.Context
import net.treelzebub.umap.R
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.util.getPrefs
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 6/7/15
 */
public class AccessTokenEvent(val c: Context, var accessToken: Token) : Event {

    override fun onSuccess() {
        TokenHolder.accessToken = accessToken
        val editor = getPrefs(c)?.edit()
        editor?.putString(c.getString(R.string.key_access_token), accessToken.token)
        editor?.putString(c.getString(R.string.key_access_token_secret), accessToken.secret)
        editor?.putString(c.getString(R.string.key_access_token_raw_response), accessToken.rawResponse)
        editor?.commit()
    }

    override fun onFailure() {
    }
}
