package net.treelzebub.umap.api

import net.treelzebub.umap.api.model.Collection
import net.treelzebub.umap.api.model.CollectionReleases
import net.treelzebub.umap.api.model.User
import net.treelzebub.umap.auth.TokenHolder

/**
 * Created by Tre Murillo on 10/11/15
 */
object DiscogsService {

    private val api: DiscogsApi
        get() = ApiModule(TokenHolder.getToken()).api

    private var username: String? = null

     fun getUsername(): String {
        if (username == null) {
            username = api.getIdentity().username
        }
        return username!!
    }

    fun getUser(): User {
        return api.getUser(getUsername())
    }

     fun getCollection(): Collection {
        return api.getCollection(getUsername())
    }

     fun getCollectionReleases(folderId: String = "0"): CollectionReleases {
        return api.getCollectionReleases(getUsername(), folderId)
    }
}
