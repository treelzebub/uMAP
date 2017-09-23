package net.treelzebub.umap.auth.user

import android.content.Context
import net.treelzebub.umap.model.User
import org.jetbrains.anko.toast

/**
 * Created by Tre Murillo on 8/6/16
 */
object Users {

    private var user: User? = null

    fun set(context: Context, user: User) {
        this.user = user
        context.toast("User Set...TODO -- persist to disk")
    }

    fun hasUser() = user != null
}

class NoUserException(msg: String = "User Not Found.") : RuntimeException(msg)