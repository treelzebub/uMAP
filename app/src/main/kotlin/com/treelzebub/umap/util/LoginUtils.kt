package com.treelzebub.umap.util

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import com.treelzebub.umap.R
import com.treelzebub.umap.async.event.AccessTokenEvent
import com.treelzebub.umap.auth.AuthService
import com.treelzebub.umap.auth.TokenHolder
import org.scribe.model.Verifier

/**
 * Created by Tre Murillo on 6/7/15
 *
 * Functions that handle the two-step nightmare that is OAuth1.0a
 */
public object LoginUtils {

    /**
     * Using the request token we retrieved earlier, get that sweet, sweet Access Token that will
     * allow access to Discogs' protected resources. This token is then broadcast by Otto and received
     * by [DashboardActivity]
     *
     * @param c: a Context used to access uMAP's SharedPreferences and String Resources.
     * @param data: the URI we caught from Discog's callback, after the user authorized the app.
     * */
    public fun requestAccessToken(c: Context, data: Uri) {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                val verifier = Verifier(data.getQueryParameter("oauth_verifier"))
                val requestToken = TokenHolder.requestToken
                val sAuthService = AuthService.instance
                val accessToken = sAuthService.getAccessToken(requestToken, verifier)
                setTokens(c, data)
                if (accessToken != null) {
                    Log.d("OAuth Token: ", accessToken.token)
                    AccessTokenEvent(c, accessToken).onSuccess()
                } else {
                    AccessTokenEvent(c, accessToken).onFailure()
                }
                return null
            }

            override fun onPostExecute(nothing: Void?) {
                UserUtils.syncUser(c)
            }
        }.execute()
    }

    private fun setTokens(c: Context, data: Uri) {
        val editor = getPrefs(c)?.edit()
        editor?.putString(c.getString(R.string.key_oauth_token), data.getQueryParameter("oauth_token"))
        editor?.putString(c.getString(R.string.key_oauth_verifier), data.getQueryParameter("oauth_verifier"))?.commit()
    }
}
