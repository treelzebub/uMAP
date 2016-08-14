package net.treelzebub.umap.activity.master_release

import android.content.Context
import android.content.Intent
import android.os.Bundle
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.conduit.onSuccess
import net.treelzebub.umap.activity.master_release.MasterReleaseMvp.Presenter
import net.treelzebub.umap.util.android.withIntent

/**
 * Created by Tre Murillo on 8/14/16
 */
class MasterReleaseActivity : UmapActivity() {

    companion object {
        fun get(c: Context, masterId: String): Intent {
            return Intent(c, MasterReleaseActivity::class.java).putExtra("master_id", masterId)
        }
    }

    private val presenter by lazy { Presenter(MasterReleaseView(this)) }
    private val masterId  by withIntent { it.getStringExtra("master_id") }
    private val conduit   = MasterReleaseConduit(this)
        .onSuccess {
            presenter.set(it!!)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_release)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        conduit.load(masterId)
    }
}