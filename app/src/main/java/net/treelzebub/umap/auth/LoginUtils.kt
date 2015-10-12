package net.treelzebub.umap.auth

import android.content.Context
import android.net.Uri
import net.treelzebub.umap.sync.SyncCenter
import net.treelzebub.umap.ui.activity.DashboardActivity
import net.treelzebub.umap.util.BusProvider
import net.treelzebub.umap.util.async
import org.scribe.model.Verifier

/**
 * Created by Tre Murillo on 6/7/15
 *
 * Functions that handle the two-step nightmare that is OAuth1.0a
 */
public object LoginUtils {

    /**
     * Using the request token we retrieved earlier, get that sweet, sweet Access Token that will
     * allow access to Discogs' protected resources.
     *
     * @param c: a Context used to access uMAP's SharedPreferences and String Resources.
     * @param data: the URI we caught from Discog's callback, after the user authorized the app.
     * */
    public fun requestAccessToken(c: Context, data: Uri) {
        BusProvider.instance.register(this)
        async {
            val verifier = Verifier(data.getQueryParameter("oauth_verifier"))
            val accessToken = AuthService.instance.getAccessToken(TokenHolder.getRequestToken(), verifier)
            if (accessToken != null) {
                TokenHolder.setToken(accessToken)
                AuthUtils.setTokenPrefs(c, accessToken)
                SyncCenter.serializeUser(c)
                c.startActivity(DashboardActivity.getIntent(c))
            } else {
                // TODO
            }
        }
    }
}
