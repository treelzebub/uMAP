package com.treelzebub.umap.auth

import android.content.Context

/**
 * Created by Tre Murillo on 8/17/15
 */
public object AuthState {

    private var sAuthRequests = 0

    private var didLogout = false
    public fun onDidLogout(c: Context) {
        if (!didLogout) {
            didLogout = true
        }
    }
}
