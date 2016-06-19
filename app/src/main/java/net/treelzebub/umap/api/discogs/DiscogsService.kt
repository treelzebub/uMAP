package net.treelzebub.umap.api.discogs

import net.treelzebub.umap.api.discogs.model.User
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.api.discogs.model.Collection
import net.treelzebub.umap.api.discogs.model.CollectionReleases

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
