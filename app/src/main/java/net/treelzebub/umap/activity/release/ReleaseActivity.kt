package net.treelzebub.umap.activity.release

import android.content.Context
import android.content.Intent
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.util.android.withIntent

/**
 * Created by Tre Murillo on 6/18/16
 */

class ReleaseActivity : UmapActivity() {

    companion object {

        fun get(c: Context, release: String): Intent {
            return Intent(c, ReleaseActivity::class.java)
                    .putExtra("release_id", release)
        }
    }

    private val releaseId: String by withIntent { it.getStringExtra("release_id") }

    private val conduit = ReleaseConduit(this).onComplete {
        if (it != null) {
            refresh()
        }
    }

    private fun refresh() {

    }


}
