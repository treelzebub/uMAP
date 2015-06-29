package com.treelzebub.umap.util

import android.content.Context
import android.os.AsyncTask
import android.util.Log
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

    platformStatic
    public fun hasUser(): Boolean {
        return user != null
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
            Log.d("User Persist Error", "User not set.")
            return false
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
        PrefsUtils.getPrefs(c)?.edit()?.putString(c.getString(R.string.key_username), u.username)?.commit()
    }

    platformStatic
    public fun usernameFromPrefs(c: Context): String {
        return PrefsUtils.getPrefs(c)?.getString(c.getString(R.string.key_username), "null") ?: "null"
    }

    platformStatic
    public fun syncUser(c: Context) {
        object : AsyncTask<Context, Void, User>() {
            override fun doInBackground(vararg params: Context): User {
                return RestService.service.getUser(UserUtils.usernameFromPrefs(params[0]))
            }

            override fun onPostExecute(result: User) {
                BusProvider.instance.post(UserEvent(result))
            }
        }.execute(c)
    }
}
