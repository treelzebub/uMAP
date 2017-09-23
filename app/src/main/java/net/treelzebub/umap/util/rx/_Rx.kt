package net.treelzebub.umap.util.rx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by treelzebub on 9/23/2017
 */


fun <T> Observable<T>.umap() = apply {
    subscribeOn(Schedulers.io())
    observeOn(AndroidSchedulers.mainThread())
}