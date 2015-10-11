package net.treelzebub.umap.api.discogs

import net.treelzebub.umap.api.discogs.model.Identity
import net.treelzebub.umap.api.discogs.model.User
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.util.async

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

    private fun getUsername(): String? {
        var retval: String? = null
        async ({
            username = api.getIdentity().username
        }, {
            retval = username
        })
        return retval
    }

//    private val user: User
//        get() = api.getUser(username)
//
//    public val avatar: String
//        get() = user.avatar_url
//
//    public val name: String
//        get() = user.name
}
