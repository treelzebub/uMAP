package net.treelzebub.umap.ui.loadinggiffragment.gif

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import net.treelzebub.umap.R
import net.treelzebub.umap.ui.loadinggiffragment.blur.BlurDialogFragment
import pl.droidsonroids.gif.GifImageView

/**
 * Created by Tre Murillo on 8/7/16
 */
open class LoadingGifFragment : BlurDialogFragment() {

    private var _dialog: Dialog? = null

    private lateinit var gradient: GradientDrawable
    private lateinit var gifImageView: GifImageView

    private val radius = 10
    private val _downScaleFactor = 5f
    private val shouldDim = true
    private val shouldBlurToolbar = false
    private val cornerRadius = 30f

    private @ColorInt var bgColor: Int = Color.MAGENTA

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (_dialog == null) {
            gradient = GradientDrawable()
            _dialog = Dialog(activity, R.style.LoadingGifDialog)
            _dialog!!.let {
                it.setContentView(R.layout.dialog_loading_gif)
                it.setCanceledOnTouchOutside(false)
                it.window.setGravity(Gravity.CENTER)
            }
            gifImageView = _dialog!!.findViewById<GifImageView>(R.id.gif_image_view)
            gradient.cornerRadius = this.cornerRadius
            gradient.setColor(bgColor)
            _dialog?.findViewById<View>(R.id.background)?.background = gradient
            gifImageView.setImageResource(getDrawableResId())
            gradient.setColor(ContextCompat.getColor(context, R.color.loading_bg))
        }

        return _dialog!!
    }

    override fun onDestroyView() {
        _dialog?.dismiss()
        _dialog = null
        super.onDestroyView()
    }

    open fun getDrawableResId(): Int {
        return R.drawable.loading_gif
    }

    fun setBackgroundColor(bgColor: Int) {
        this.bgColor = bgColor
    }

    override fun getBlurRadius(): Int {
        return radius
    }

    override fun shouldBlurToolbar(): Boolean {
        return shouldBlurToolbar
    }

    override fun getDownScaleFactor(): Float {
        return _downScaleFactor
    }

    override fun shouldDim(): Boolean {
        return shouldDim
    }
}
