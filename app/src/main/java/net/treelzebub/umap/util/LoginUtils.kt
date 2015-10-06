package net.treelzebub.umap.util

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import net.treelzebub.umap.R
import net.treelzebub.umap.api.discogs.model.Identity
import net.treelzebub.umap.async.event.AccessTokenEvent
import net.treelzebub.umap.auth.AuthService
import net.treelzebub.umap.auth.RestService
import net.treelzebub.umap.auth.TokenHolder
import org.scribe.model.OAuthRequest
import org.scribe.model.Response
import org.scribe.model.Verb
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
        async({
            val verifier = Verifier(data.getQueryParameter("oauth_verifier"))
            val requestToken = TokenHolder.requestToken
            val accessToken = AuthService.instance.getAccessToken(requestToken, verifier)
            setTokens(c, data)
            if (accessToken != null) {
                Log.d("OAuth Token: ", accessToken.token)

                // todo...
                val request = OAuthRequest(Verb.GET, "https://api.discogs.com/oauth/identity")
                AuthService.instance.signRequest(accessToken, request)
                val response = request.send()
                val identity = Gson().fromJson(response.body, Identity::class.java)
                identity.username

                AccessTokenEvent(c, accessToken).onSuccess()
            } else {
                AccessTokenEvent(c, accessToken).onFailure()
            }
        }, {
            UserUtils.syncUser(c)
        })
    }

    private fun setTokens(c: Context, data: Uri) {
        val editor = getPrefs(c)?.edit()
        editor?.putString(c.getString(R.string.key_oauth_token), data.getQueryParameter("oauth_token"))
        editor?.putString(c.getString(R.string.key_oauth_verifier), data.getQueryParameter("oauth_verifier"))?.commit()
    }
}
