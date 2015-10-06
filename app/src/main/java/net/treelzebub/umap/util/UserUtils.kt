package net.treelzebub.umap.util

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import net.treelzebub.umap.R
import net.treelzebub.umap.api.discogs.model.User
import net.treelzebub.umap.auth.RestService
import net.treelzebub.umap.ui.UserEvent

/**
 * Created by Tre Murillo on 6/7/15
 */
public object UserUtils {

    private var user: User? = null
    private var username: String? = null

    public fun getUser(): User? {
        return user
    }

    public fun getUsername(): String? {
        return username
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
                try {
                    return RestService.instance.getUser(UserUtils.usernameFromPrefs(params[0]))
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
