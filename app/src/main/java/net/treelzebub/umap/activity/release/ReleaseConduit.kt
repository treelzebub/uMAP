package net.treelzebub.umap.activity.release

import android.os.Bundle
import com.levelmoney.conduit.Conduit
import net.treelzebub.umap.model.Release
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.response.Response

/**
 * Created by Tre Murillo on 6/18/16
 */
class ReleaseConduit : Conduit<ReleaseConduit, Response<Release>> {

    constructor(a: ReleaseActivity) : super(a)

    override fun onLoad(args: Bundle): Response<Release>? {
        val releaseId = args.getString("release_id")
        return Discogs.connect {
            getRelease(releaseId)
        }
    }

    fun load(id: String) {
        load(Bundle().apply { putString("release_id", id) })
    }
}
