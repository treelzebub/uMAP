package net.treelzebub.umap.bismarck

import android.content.Context
import android.util.Log
import com.levelmoney.bismarck.Bismarck
import com.levelmoney.bismarck.Bismarcks
import com.levelmoney.bismarck.RateLimiter
import com.levelmoney.bismarck.listen
import com.levelmoney.bismarck.serializers.SerializableSerializer
import net.treelzebub.umap.util.bus.BusProvider
import rx.Observer
import java.io.Serializable

/**
 * Created by Tre Murillo on 8/6/16
 */

inline fun <reified D : Serializable> Bismarcks.discogs(key: String): DiscogsBismarck<D> = DiscogsBismarck(key)

inline fun <reified D : Serializable> Bismarcks.api(c: Context, key: String, rateLimiter: RateLimiter = AlwaysRateLimiter()): DiscogsBismarck<D> {
    return discogs<D>(key)
                .rateLimiter(rateLimiter)
                .persister(UmapFilePersister(c, key, SerializableSerializer<D>()))
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
        onError.invoke(e)
    }

    override fun onCompleted() {

    }
}
