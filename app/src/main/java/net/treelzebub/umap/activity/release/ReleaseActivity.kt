package net.treelzebub.umap.activity.release

import android.os.Bundle
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.base.BaseReleaseActivity
import net.treelzebub.umap.activity.base.adapter.TracklistAdapter
import net.treelzebub.umap.activity.base.mvp.ReleasePresenter
import net.treelzebub.umap.activity.base.mvp.ReleaseView

/**
 * Created by Tre Murillo on 6/18/16
 */
class ReleaseActivity : BaseReleaseActivity() {

    private val presenter by lazy { ReleasePresenter(this, ReleaseView(this)) }
    override val adapter  = TracklistAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_release) //TODO different layout
    }
}
