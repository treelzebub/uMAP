package net.treelzebub.umap.activity.master_release

import android.os.Bundle
import com.levelmoney.conduit.Conduit
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import net.treelzebub.umap.model.MasterRelease
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.response.Response

/**
 * Created by Tre Murillo on 8/14/16
 */
class MasterReleaseConduit : Conduit<MasterReleaseConduit, Response<MasterRelease>> {

    constructor(a: ObserveAppCompatActivity) : super(a)

    override fun onLoad(args: Bundle): Response<MasterRelease>? {
        val masterId = args.getString("master_id")
        return Discogs.connect {
            getMasterRelease(masterId)
        }
    }
}
