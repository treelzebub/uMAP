package com.treelzebub.umap.auth

import android.content.Context
import com.treelzebub.umap.util.UserUtils

/**
 * Created by Tre Murillo on 8/17/15
 */
public object AuthState {
    private synchronized var sAuthRequests = 0

    private var didLogout = false
    public fun onDidLogout(c: Context) {
        if(!didLogout) {
            didLogout = true
        }
    }
}
