package com.treelzebub.umap.async

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import com.treelzebub.umap.api.discogs.constants.AUTH_URL_APPEND
import com.treelzebub.umap.api.discogs.constants.CALLBACK_URL
import com.treelzebub.umap.api.discogs.constants.CONSUMER_KEY
import com.treelzebub.umap.api.discogs.constants.CONSUMER_SECRET
import com.treelzebub.umap.async.event.AccessTokenEvent
import com.treelzebub.umap.async.event.AuthUrlEvent
import com.treelzebub.umap.auth.DiscogsApi
import com.treelzebub.umap.util.BusProvider
import com.treelzebub.umap.util.TokenHolder
import org.scribe.builder.ServiceBuilder
import org.scribe.exceptions.OAuthException
import org.scribe.model.Verifier
import org.scribe.oauth.OAuthService

/**
 * Created by Tre Murillo on 6/7/15
 *
 * Functions that handle the two-step nightmare that is OAuth1.0a
 */
public fun getOAuthService(): OAuthService {
    return ServiceBuilder()
            .apiKey(CONSUMER_KEY)
            .apiSecret(CONSUMER_SECRET)
            .callback(CALLBACK_URL)
            .provider(javaClass<DiscogsApi>())
            .build()
}

/**
 * Get a RequestToken from Discogs, then build a URL that will allow login, then authorization
 * */
public fun retrieveAuthUrl() {
    object : AsyncTask<Void, Void, Void>() {
        var authUrl = "http://treelzebub.net"
        override fun doInBackground(vararg params: Void): Void? {
            try {
                val rt = getOAuthService().getRequestToken()
                TokenHolder.setRequestToken(rt)
                authUrl = getOAuthService().getAuthorizationUrl(rt) + AUTH_URL_APPEND + rt.getToken()
            } catch (e: OAuthException) {
                Log.e("OAuthException: ", e.getMessage())
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            BusProvider.getInstance.post(AuthUrlEvent(authUrl))
        }
    }.execute()
}

/**
 * Using the request token we retrieved earlier, get that sweet, sweet Access Token that will
 * allow access to Discogs' protected resources. This token is then broadcast by Otto and received
 * by {@link DashboardActivity}
 *
 * @param c: a Context used to access uMAP's SharedPreferences and String Resources.
 * @param data: the URI we caught from Discog's callback, after the user authorized the app.
 * */
public fun requestAccessToken(c: Context, data: Uri) {
    object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            val requestToken = TokenHolder.getRequestToken()
            val verifier = Verifier(data.getQueryParameter("oauth_verifier"))
            val service = ServiceBuilder()
                    .apiKey(CONSUMER_KEY)
                    .apiSecret(CONSUMER_SECRET)
                    .callback(CALLBACK_URL)
                    .provider(javaClass<DiscogsApi>())
                    .build()
            val accessToken = service.getAccessToken(requestToken, verifier)
            if (accessToken != null) {
                Log.d("OAuth Token: ", accessToken.getToken())
                AccessTokenEvent(c, accessToken).onSuccess()
            } else {
                AccessTokenEvent(c, accessToken).onFailure()
            }

            return null
        }
    }.execute()
}