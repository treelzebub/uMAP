package net.treelzebub.umap.auth

import android.content.Context
import android.util.Log
import net.treelzebub.umap.model.User
import net.treelzebub.umap.util.TAG

/**
 * Created by Tre Murillo on 8/6/16
 */
object Users {

    private var user: User? = null

    fun set(context: Context, user: User) {
        Users.user = user
        Log.d(TAG, "User Set...TODO -- persist to disk")
    }

    fun hasUser() = user != null
}

class NoUserException(msg: String = "User Not Found.") : RuntimeException(msg)