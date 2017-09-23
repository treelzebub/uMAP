package net.treelzebub.umap.activity.master

import android.content.Context
import android.content.Intent
import android.os.Bundle
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.base.BaseReleaseActivity
import net.treelzebub.umap.activity.base.adapter.TracklistAdapter
import net.treelzebub.umap.activity.base.mvp.ReleasePresenter
import net.treelzebub.umap.activity.base.mvp.ReleaseView
import net.treelzebub.umap.util.android.withIntent

/**
 * Created by Tre Murillo on 8/14/16
 */
class MasterReleaseActivity : BaseReleaseActivity() {

    companion object {
        fun get(c: Context, masterId: String): Intent {
            return Intent(c, MasterReleaseActivity::class.java).putExtra("master_id", masterId)
        }
    }

    private val masterId: String?  by withIntent { it.getStringExtra("master_id") }

    private val presenter by lazy { ReleasePresenter(ReleaseView(this)) }
    override val adapter  = TracklistAdapter()

//    private val conduit = MasterReleaseConduit(this)
//        .withSpinner()
//        .onSuccess {
//            presenter.set(it!!)
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_release)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
//        val lastMaster = Data.lastMaster.peek()?.id.toString()
//        conduit.load(masterId ?: lastMaster)
    }
}