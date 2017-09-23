package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.model.User
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 8/6/16
 */
object AuthState {

    private var user: User? = null
    private var token: Token? = null

    fun setUser(context: Context, user: User) {
        this.user = user
    }

    fun setToken(context: Context, token: Token) {
        this.token = token
    }

    fun isLoggedIn() = user != null && token != null

    fun logout() {

    }
}

class NoUserException(msg: String = "User Not Found.") : RuntimeException(msg)