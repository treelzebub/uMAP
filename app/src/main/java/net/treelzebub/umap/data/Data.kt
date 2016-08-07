package net.treelzebub.umap.data

import com.levelmoney.bismarck.Bismarcks
import net.treelzebub.umap.api.model.CollectionReleases
import net.treelzebub.umap.api.model.User

/**
 * Created by Tre Murillo on 8/6/16.
 */
object Data {

    var user = Bismarcks.baseBismarck<User>()

    var userCollection = Bismarcks.baseBismarck<CollectionReleases>()
}
