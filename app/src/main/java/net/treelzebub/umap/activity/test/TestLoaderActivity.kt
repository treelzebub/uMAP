package net.treelzebub.umap.activity.test

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.google.gson.GsonBuilder
import com.levelmoney.conduit.Conduit
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.activity.collection.CollectionConduit
import net.treelzebub.umap.activity.master_release.MasterReleaseConduit
import net.treelzebub.umap.conduit.withSpinner
import net.treelzebub.umap.data.Data
import net.treelzebub.umap.model.Listing
import net.treelzebub.umap.net.api.Discogs
import net.treelzebub.umap.net.response.Response
import net.treelzebub.umap.util.android.UmapVersions
import net.treelzebub.umap.util.android.onClick
import net.treelzebub.umap.util.android.subscribeToBismarck
import net.treelzebub.umap.util.android.toast
import org.jetbrains.anko.find

/**
 * Created by Tre Murillo on 8/7/16
 */
class TestLoaderActivity : UmapActivity() {

    private val testListing = TestListingConduit(this)
        .withSpinner()

    private val testCollection = MasterReleaseConduit(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        subscribeToBismarck(Data.collection) {
            Log.d("Collection Contents", GsonBuilder().setPrettyPrinting().create().toJson(it))
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        onClick(R.id.button) {
            val id = find<EditText>(R.id.listing_id).text.toString()
            Log.d("Trying to load", id)
            testListing.load(id)
        }

        onClick(R.id.button2) {
            val id = find<EditText>(R.id.master_id).text.toString()
            testCollection.load(id)
        }
    }
}

class TestListingConduit(a: TestLoaderActivity) : Conduit<TestListingConduit, Response<Listing>>(a) {

    override fun onLoad(args: Bundle): Response<Listing>?{
        val releaseId = args.getString("release_id")
        return Discogs.connect {
            getListing(releaseId)
        }
    }

    fun load(id: String) {
        load(Bundle().apply { putString("release_id", id)})
    }
}