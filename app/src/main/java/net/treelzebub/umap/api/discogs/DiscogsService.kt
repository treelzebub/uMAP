package net.treelzebub.umap.api.discogs

import net.treelzebub.umap.api.discogs.model.User
import net.treelzebub.umap.auth.TokenHolder
import net.treelzebub.umap.api.discogs.model.Collection
import net.treelzebub.umap.api.discogs.model.CollectionReleases

/**
 * Created by Tre Murillo on 10/11/15
 */
public object DiscogsService {

    private val api: DiscogsApi
        get() = ApiModule(TokenHolder.token(), TokenHolder.tokenSecret()).get()

    private var username: String? = null

    public fun getUsername(): String {
        if (username == null) {
            username = api.getIdentity().username
        }
        return username!!
    }

    public fun getUser(): User {
        return api.getUser(getUsername())
    }

    public fun getCollection(): Collection {
        return api.getCollection(getUsername())
    }

    public fun getCollectionReleases(folderId: String = "0"): CollectionReleases {
        return api.getCollectionReleases(getUsername(), folderId)
    }
}
