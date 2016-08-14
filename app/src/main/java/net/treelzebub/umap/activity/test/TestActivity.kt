package net.treelzebub.umap.activity.test

import android.os.Bundle
import android.os.Looper
import com.levelmoney.conduit.Conduit
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.UmapActivity
import net.treelzebub.umap.async.post
import net.treelzebub.umap.conduit.withSpinner
import net.treelzebub.umap.util.android.onClick

/**
 * Created by Tre Murillo on 8/7/16.
 */
class TestActivity : UmapActivity() {

    private val test = TestConduit(this)
        .withSpinner()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        onClick(R.id.button) {
            test.load(null)
        }
    }
}

class TestConduit(a: TestActivity) : Conduit<TestConduit, Unit>(a) {

    override fun onLoad(args: Bundle?) {
        if (Looper.myLooper() == null) {
            Looper.prepare()
        }
        Thread.sleep(10000L)
    }
}