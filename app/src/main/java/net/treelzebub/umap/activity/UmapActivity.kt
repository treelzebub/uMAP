package net.treelzebub.umap.activity

import android.os.Bundle
import android.util.Log
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import net.treelzebub.umap.util.bus.BusProvider
import net.treelzebub.umap.util.kotlin.TAG
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by Tre Murillo on 6/18/16
 */

open class UmapActivity : ObserveAppCompatActivity() {


    private val subscription = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.instance.unregister(this)
    }

    fun <T> subscribe(observable: Observable<T>, fn: (T) -> Unit) {
        val sub = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(fn) {
                    Log.e(TAG, "", it)
                }
        subscription.add(sub)
    }
}
