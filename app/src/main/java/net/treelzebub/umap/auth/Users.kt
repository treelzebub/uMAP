package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.model.User
import net.treelzebub.umap.util.android.loadFile
import net.treelzebub.umap.util.android.persist
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 8/6/16
 */
object Users {

    private const val FILE_TOKEN = "umap.token"
    private const val FILE_USER = "umap.user"

    var user: User? = null
        private set

    var token: Token? = null
        private set

    fun setToken(context: Context, token: Token) {
        this.token = token
        token.persist(context, FILE_TOKEN)
    }

    fun setUser(context: Context, user: User) {
        this.user = user
        user.persist(context, FILE_USER)
    }

    fun isLoggedIn() = user != null && token != null

    fun load(context: Context): Boolean {
        if (isLoggedIn()) return true
        user = context.loadFile(FILE_USER)
        token = context.loadFile(FILE_TOKEN)
        return isLoggedIn()
    }
}

class NoUserException(msg: String = "User Not Found.") : RuntimeException(msg)