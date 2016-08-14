package net.treelzebub.umap.activity.master_release

import android.os.Bundle
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.conduit.onSuccess
import net.treelzebub.umap.activity.master_release.MasterReleaseMvp.Presenter

/**
 * Created by Tre Murillo on 8/14/16
 */
class MasterReleaseActivity : UmapActivity() {

    private val presenter by lazy { Presenter(MasterReleaseView(this)) }
    private val conduit   = MasterReleaseConduit(this)
        .onSuccess {
            presenter.set(it!!)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_release)

    }
}