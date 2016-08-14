package net.treelzebub.umap.bismarck

import com.levelmoney.bismarck.RateLimiter

/**
 * Created by Tre Murillo on 8/13/16
 */
class AlwaysRateLimiter : RateLimiter {
    override val lastReset = 0L
    override fun isFresh() = true
    override fun reset() {}
    override fun update() {}
}
