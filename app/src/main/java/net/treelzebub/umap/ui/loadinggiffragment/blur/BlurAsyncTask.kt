package net.treelzebub.umap.ui.loadinggiffragment.blur

import android.graphics.Bitmap
import android.graphics.Rect
import android.os.AsyncTask
import android.support.v4.app.FragmentActivity
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * Created by Tre Murillo on 8/7/16.
 */
class BlurAsyncTask : AsyncTask<Void, Void, Void> {

    private val activity: FragmentActivity
    private val engine: BlurDialogEngine
    private val backgroundView: View

    private lateinit var background: Bitmap

    constructor(activity: FragmentActivity, engine: BlurDialogEngine) : super() {
        this.activity       = activity
        this.engine         = engine
        this.backgroundView = activity.window.decorView
        setupBackgroundView()
    }

    private fun setupBackgroundView() {
        val rect = Rect()
        backgroundView.let {
            it.getWindowVisibleDisplayFrame(rect)
            val drawingCache = rebuildDrawingCache(it)

            // After a screen config change, DecorView has no height or width, and getDrawingCache
            // will return null. In this case, we'll force measure and layout.
            if (drawingCache == null) {
                it.measure(
                        View.MeasureSpec.makeMeasureSpec(rect.width(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(rect.height(), View.MeasureSpec.EXACTLY)
                )
                it.layout(0, 0, it.measuredWidth, it.measuredHeight)
                this.background = rebuildDrawingCache(it)!!
            } else {
                this.background = drawingCache
            }
        }

    }

    private fun rebuildDrawingCache(view: View): Bitmap? {
        return view.let {
            it.destroyDrawingCache()
            it.isDrawingCacheEnabled = true
            it.buildDrawingCache(true)
            it.drawingCache
        }
    }

    override fun doInBackground(vararg params: Void?): Void? {
        if (!isCancelled) {
            engine.blur(background, backgroundView)
        } else {
            return null
        }
        background.recycle()
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)

        backgroundView.let {
            it.destroyDrawingCache()
            it.isDrawingCacheEnabled = false
        }
        activity.window.addContentView(engine.blurredBackgroundView,
                                       engine.blurredBackgroundLayoutParams)
        engine.blurredBackgroundView?.let {
            it.alpha = 0f
            it.animate()
              .alpha(1f)
              .setDuration(engine.animationDuration)
              .setInterpolator(LinearInterpolator())
              .start()
        }

        //TODO set backgroundView + background to null?
    }
}
