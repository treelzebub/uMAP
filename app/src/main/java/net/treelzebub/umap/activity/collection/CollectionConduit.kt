package net.treelzebub.umap.activity.collection

import android.os.Bundle
import com.levelmoney.conduit.Conduit
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import net.treelzebub.umap.auth.user.Users
import net.treelzebub.umap.model.CollectionReleases
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.response.Response

/**
 * Created by treelzeub on 8/7/16
 */
class CollectionConduit : Conduit<CollectionConduit, Response<CollectionReleases>> {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle?): Response<CollectionReleases>? {
        val username = Users.username ?: ""
        return Discogs.connect {
            getCollectionReleases(username, "0") // 0 is the "All" folder
        }
    }
}
