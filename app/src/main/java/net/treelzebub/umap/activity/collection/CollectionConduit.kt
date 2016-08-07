package net.treelzebub.umap.activity.collection

import android.os.Bundle
import com.levelmoney.conduit.Conduit
import com.levelmoney.observefragment.ObserveSupportFragment
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import net.treelzebub.umap.api.Discogs
import net.treelzebub.umap.api.model.CollectionReleases
import net.treelzebub.umap.auth.user.Users

/**
 * Created by Tre Murillo on 8/7/16.
 */
class CollectionConduit : Conduit<CollectionConduit, CollectionReleases?> {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle?): CollectionReleases? {
        val username = Users.username ?: return null
        return Discogs.connect {
            getCollectionReleases(username, "0")
        }
    }
}
