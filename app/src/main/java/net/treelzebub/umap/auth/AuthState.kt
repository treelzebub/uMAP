package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.model.User
import net.treelzebub.umap.util.android.loadFile
import net.treelzebub.umap.util.android.persist
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 8/6/16
 */
object AuthState {

    private const val FILE_TOKEN = "umap.token"
    private const val FILE_USER = "umap.user"

    private var user: User? = null
    private var token: Token? = null

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
        user = context.loadFile(FILE_USER)
        token = context.loadFile(FILE_TOKEN)
        return isLoggedIn()
    }
}

class NoUserException(msg: String = "User Not Found.") : RuntimeException(msg)