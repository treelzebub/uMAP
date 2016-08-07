package net.treelzebub.umap.activity.release

import android.os.Bundle
import com.levelmoney.conduit.Conduit
import net.treelzebub.umap.api.Discogs
import net.treelzebub.umap.api.model.Release

/**
 * Created by Tre Murillo on 6/18/16
 */

class ReleaseConduit(a: ReleaseActivity) : Conduit<ReleaseConduit, Release>(a) {

    override fun onLoad(args: Bundle): Release? {
        val releaseId = args.getString("release_id")
        return Discogs.connect {
            getRelease(releaseId)
        }
    }
}
