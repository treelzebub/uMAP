package net.treelzebub.umap.api.discogs

import net.treelzebub.umap.api.discogs.model.Identity
import net.treelzebub.umap.api.discogs.model.User
import net.treelzebub.umap.auth.TokenHolder

/**
 * Created by Tre Murillo on 10/11/15
 * Copyright(c) 2015 Level, Inc.
 */
public object DiscogsService {

    private val api: DiscogsApi
        get() = ApiModule(TokenHolder.token(), TokenHolder.tokenSecret()).get()

    private var username: String? = null

    public fun getIdentity(): Identity {
        return api.getIdentity()
    }

    public fun getUsername(): String {
        return username ?: api.getIdentity().username
    }

    public fun getUser(): User {
        return api.getUser(getUsername())
    }

    //    public val avatar: String
    //        get() = user.avatar_url
    //
    //    public val name: String
    //        get() = user.name
}
