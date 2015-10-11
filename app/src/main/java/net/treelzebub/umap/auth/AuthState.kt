package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.sync.SyncCenter

/**
 * Created by Tre Murillo on 8/17/15
 */
public object AuthState {

    private var isLoggedIn = false

    public fun isLoggedIn(c: Context): Boolean {
        return SyncCenter.deserializeUser(c) != null
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
