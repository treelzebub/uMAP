package com.treelzebub.umap.async

import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.constants.AUTH_URL_APPEND
import com.treelzebub.umap.api.discogs.constants.CALLBACK_URL
import com.treelzebub.umap.api.discogs.constants.CONSUMER_KEY
import com.treelzebub.umap.api.discogs.constants.CONSUMER_SECRET
import com.treelzebub.umap.async.event.AccessTokenEvent
import com.treelzebub.umap.async.event.LoginEvent
import com.treelzebub.umap.auth.DiscogsApi
import com.treelzebub.umap.util.TokenHolder
import com.treelzebub.umap.util.getPrefs
import org.scribe.builder.ServiceBuilder
import org.scribe.model.Verifier
import org.scribe.oauth.OAuthService
import kotlin.com.treelzebub.umap.util.BusProvider

/**
 * Created by Tre Murillo on 6/7/15
 */
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
            TokenHolder.setAccessToken(accessToken)
            val editor = getPrefs(c)?.edit()
            editor?.putString(c.getString(R.string.key_access_token), accessToken.getToken())
            editor?.putString(c.getString(R.string.key_access_token_secret), accessToken.getSecret())
            editor?.putString(c.getString(R.string.key_access_token_raw_response), accessToken.getRawResponse())
            editor?.commit()
            Log.d("OAuth Token: ", accessToken.getToken())
            return null
        }

        override fun onPostExecute(result: Void?) {
            BusProvider.getInstance().post(AccessTokenEvent())
        }
    }.execute()
}

public fun login() {
    object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            val rt = getOAuthService().getRequestToken()
            TokenHolder.setRequestToken(rt)
            val authUrl = getOAuthService().getAuthorizationUrl(rt) + AUTH_URL_APPEND + rt.getToken()
            BusProvider.getInstance().post(LoginEvent(authUrl))
            return null
        }

        override fun onPostExecute(result: Void?) {

        }
    }.execute()
}

public fun getOAuthService(): OAuthService {
    return ServiceBuilder()
            .apiKey(CONSUMER_KEY)
            .apiSecret(CONSUMER_SECRET)
            .callback(CALLBACK_URL)
            .provider(javaClass<DiscogsApi>())
            .build()
}