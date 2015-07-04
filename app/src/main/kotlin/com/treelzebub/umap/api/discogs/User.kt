package com.treelzebub.umap.api.discogs

import com.treelzebub.umap.util.PrefsUtils

/**
 * Created by Tre Murillo on 7/4/15
 */
public data class User(val username: String,
                       val name: String?,
                       val homePage: String?,
                       val location: String?,
                       val profile: String?)

public fun setUser(user: User) {
    PrefsUtils.
}