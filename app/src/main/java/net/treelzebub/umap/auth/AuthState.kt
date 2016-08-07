package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.auth.user.Users
import net.treelzebub.umap.inject.ContextInjection
import net.treelzebub.umap.sync.Files

/**
 * Created by Tre Murillo on 8/17/15
 */
object AuthState {

    fun isLoggedIn(c: Context = ContextInjection.c): Boolean {
        return Users.deserializeUser() != null
                && AuthUtils.getTokenPrefs(c)
    }
}
