package net.treelzebub.umap.auth

import net.treelzebub.umap.auth.user.Users

/**
 * Created by Tre Murillo on 8/17/15
 */
object AuthState {

    fun isLoggedIn(): Boolean {
        return Users.hasUser() //&& TokenHolder.hasAccessToken()
    }
}
