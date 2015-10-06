package net.treelzebub.umap.auth

import android.content.Context

/**
 * Created by Tre Murillo on 8/17/15
 */
public object AuthState {

    private var isLoggedIn = false

    public fun isLoggedIn(): Boolean {
        return isLoggedIn
    }

    public fun setIsLoggedIn(bool: Boolean) {
        isLoggedIn = bool
    }

    public fun onDidLogout(c: Context) {
        if (!isLoggedIn) {
            isLoggedIn = true
        }
    }
}
