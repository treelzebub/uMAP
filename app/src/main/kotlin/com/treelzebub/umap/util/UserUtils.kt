package com.treelzebub.umap.util

import android.content.Context
import android.util.Log
import com.treelzebub.umap.api.discogs.constants.USER_FILENAME
import com.treelzebub.umap.api.discogs.model.User
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

/**
 * Created by Tre Murillo on 6/7/15
 */
public class UserUtils() : Serializable {

    var user: User? = null

    public fun hasUser(): Boolean {
        return user != null
    }

    public fun persist(c: Context): Boolean {
        if (hasUser()) {
            try {
                val fileOutStream = c.openFileOutput(USER_FILENAME, Context.MODE_PRIVATE)
                var objOutStream = ObjectOutputStream(fileOutStream)
                objOutStream.writeObject(this)
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
}