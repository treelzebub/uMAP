package com.treelzebub.umap.util

import android.content.Context
import android.os.AsyncTask
import com.treelzebub.umap.R
import com.treelzebub.umap.USER_FILENAME
import com.treelzebub.umap.api.discogs.model.User
import com.treelzebub.umap.async.event.UserEvent
import com.treelzebub.umap.auth.RestService
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlin.platform.platformStatic

/**
 * Created by Tre Murillo on 6/7/15
 */
public object UserUtils {

    var user: User? = null
    var username: String? = null

    platformStatic
    public fun hasUser(c: Context): Boolean {
        return user != null || username != null
    }

    platformStatic
    public fun toFile(c: Context, u: User?): Boolean {
        if (u != null) {
            try {
                val fileOutStream = c.openFileOutput(USER_FILENAME, Context.MODE_PRIVATE)
                var objOutStream = ObjectOutputStream(fileOutStream)
                objOutStream.writeObject(u)
                objOutStream.close()
                return true
            } catch(e: IOException) {
                e.printStackTrace()
                return false
            }
        } else {
            throw NoUserException("User Persist Error")
        }
    }

    platformStatic
    public fun fromFile(c: Context): Boolean {
        try {
            val fileInStream = c.openFileInput(USER_FILENAME)
            val objInStream = ObjectInputStream(fileInStream)
            user = objInStream.readObject() as User
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }

    platformStatic
    public fun usernameToPrefs(c: Context, u: User) {
        getPrefs(c)?.edit()?.putString(c.getString(R.string.key_pref_username), u.username)?.commit()
    }

    platformStatic
    public fun usernameFromPrefs(c: Context): String {
        val username = getPrefs(c)?.getString(c.getString(R.string.key_pref_username), null)
        if (username != null) {
            this.username = username
            return username
        } else {
            throw NoUserException("SharedPrefs does not contain user.")
        }
    }

    platformStatic
    public fun syncUser(c: Context) {
        object : AsyncTask<Context, Void, User>() {
            override fun doInBackground(vararg params: Context): User {
                var userFromPrefs: String
                try {
                    userFromPrefs = UserUtils.usernameFromPrefs(params[0])
                    return RestService.instance.getUser(userFromPrefs)
                } catch (e: NoUserException) {
                    val identity = RestService.instance.getIdentity()
                    //TODO this is unsafe
                    return RestService.instance.getUser(identity.username!!)
                }
            }

            override fun onPostExecute(result: User) {
                BusProvider.instance.post(UserEvent(result))
            }
        }.execute(c)
    }

    public class NoUserException(message: String?) : Exception(message)
}
