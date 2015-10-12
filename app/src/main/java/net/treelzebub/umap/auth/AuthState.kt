package net.treelzebub.umap.auth

import android.content.Context
import net.treelzebub.umap.sync.SyncCenter

/**
 * Created by Tre Murillo on 8/17/15
 */
public object AuthState {

    public fun isLoggedIn(c: Context): Boolean {
        return SyncCenter.deserializeUser(c) != null && AuthUtils.getTokenPrefs(c)
    }
}
