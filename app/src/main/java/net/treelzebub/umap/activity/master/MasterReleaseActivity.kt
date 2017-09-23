package net.treelzebub.umap.activity.master

import android.os.Bundle
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.base.BaseReleaseActivity
import net.treelzebub.umap.activity.base.adapter.TracklistAdapter
import net.treelzebub.umap.activity.base.mvp.ReleasePresenter
import net.treelzebub.umap.activity.base.mvp.ReleaseView
import net.treelzebub.umap.net.api.Discogs
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Tre Murillo on 8/14/16
 */
class MasterReleaseActivity : BaseReleaseActivity() {

    private val presenter by lazy { ReleasePresenter(this, ReleaseView(this)) }

    override val adapter  = TracklistAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_release)

        doAsync {
            Discogs.api.getRelease(releaseId).subscribe {
                data ->
                uiThread { presenter.set(data) }
            }
        }
    }
}