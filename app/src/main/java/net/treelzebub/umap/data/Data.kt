package net.treelzebub.umap.data

import com.levelmoney.bismarck.Bismarcks
import net.treelzebub.umap.model.CollectionReleases
import net.treelzebub.umap.model.MasterRelease
import net.treelzebub.umap.model.User

/**
 * Created by Tre Murillo on 8/6/16
 */
object Data {

    var user = Bismarcks.baseBismarck<User>()

    var collection = Bismarcks.baseBismarck<CollectionReleases>()
    var lastMaster = Bismarcks.baseBismarck<MasterRelease>()
}
