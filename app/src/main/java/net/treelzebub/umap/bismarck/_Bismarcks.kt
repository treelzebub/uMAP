package net.treelzebub.umap.bismarck

import android.util.Log
import com.levelmoney.bismarck.Bismarck
import com.levelmoney.bismarck.Bismarcks
import com.levelmoney.bismarck.RateLimiter
import com.levelmoney.bismarck.android.persisters.AndroidPersisters
import com.levelmoney.bismarck.impl.BaseBismarck
import com.levelmoney.bismarck.listen
import com.levelmoney.bismarck.serializers.SerializableSerializer
import net.treelzebub.umap.auth.user.Users
import net.treelzebub.umap.inject.ContextInjection
import net.treelzebub.umap.util.bus.BusProvider
import rx.Observer
import java.io.Serializable

/**
 * Created by Tre Murillo on 8/6/16.
 */

inline fun <reified D : Serializable> Bismarcks.discogs(key: String): DiscogsBismarck<D> = DiscogsBismarck(key)

inline fun <reified D : Serializable> Bismarcks.api(key: String, rateLimiter: RateLimiter): BaseBismarck<D> {
    val c = ContextInjection.c
    return Bismarcks.discogs<D>(key)
        .rateLimiter(rateLimiter)
        .persister(AndroidPersisters.account(c, key, Users.accountType, SerializableSerializer<D>(), false))
        .listen { Log.d("Bismarck", "Inserting: $key") }
        .ottoSubscribe() as DiscogsBismarck<D>
}

fun <T : Any> Bismarck<T>.ottoSubscribe() = apply {
    observe().subscribe(OttoSubscribe<T?>() {
        invalidate()
        refresh()
    })
}

class OttoSubscribe<T>(val onError: (Throwable?) -> Unit) : Observer<T> {
    override fun onNext(t: T) {
        BusProvider.instance.post(t ?: return)
    }

    override fun onError(e: Throwable?) {
        // TODO crashlytics reporting
        onError.invoke(e)
    }

    override fun onCompleted() {

    }
}
