package com.treelzebub.umap.util

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.treelzebub.umap.R
import com.treelzebub.umap.USER_FILENAME
import com.treelzebub.umap.api.discogs.model.User
import com.treelzebub.umap.auth.RestService
import com.treelzebub.umap.auth.TokenHolder
import com.treelzebub.umap.ui.UserEvent
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * Created by Tre Murillo on 6/7/15
 */
public object UserUtils {

    var user: User? = null
    var username: String? = null

    public fun isLoggedIn(c: Context): Boolean {
        return TokenHolder.hasAccessToken(c) && UserUtils.hasUser(c)
    }

    public fun hasUser(c: Context): Boolean {
        return getUsername(c) != null
    }

    public fun getUser(c: Context): User? {
        return user
    }

    public fun getUsername(c: Context): String? {
        return getPrefs(c)?.getString(c.getString(R.string.key_pref_username), null)
    }

    public fun usernameToPrefs(c: Context, u: User) {
        getPrefs(c)?.edit()?.putString(c.getString(R.string.key_pref_username), u.username)?.commit()
    }

    public fun usernameFromPrefs(c: Context): String {
        val username = getPrefs(c)?.getString(c.getString(R.string.key_pref_username), null)
        if (username != null) {
            this.username = username
            return username
        } else {
            throw NoUserException("SharedPrefs does not contain user.")
        }
    }

    public fun syncUser(c: Context) {
        object : AsyncTask<Context, Void?, User>() {
            override fun doInBackground(vararg params: Context): User? {
                var userFromPrefs: String
                try {
                    userFromPrefs = UserUtils.usernameFromPrefs(params[0])
                    return RestService.instance.getUser(userFromPrefs)
                } catch (e: NoUserException) {
                    Log.e("NoUserException", e.getMessage())
                }
                return null
            }

            override fun onPostExecute(result: User?) {
                BusProvider.instance.post(UserEvent(result))
            }
        }.execute(c)
    }

    public class NoUserException(message: String?) : Exception(message)
}
