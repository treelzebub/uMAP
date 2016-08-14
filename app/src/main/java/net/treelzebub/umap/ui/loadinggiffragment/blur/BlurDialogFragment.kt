package net.treelzebub.umap.ui.loadinggiffragment.blur

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import net.treelzebub.umap.R

/**
 * Created by Tre Murillo on 8/7/16.
 */
open class BlurDialogFragment : DialogFragment() {

    private var blurEngine: BlurDialogEngine? = null
    private var toolbar:    Toolbar?          = null
    private var shouldDim:  Boolean           = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (blurEngine != null) {
            // Fragment is re-attaching.
            blurEngine!!.onAttach(activity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        blurEngine = BlurDialogEngine(activity)
        if (toolbar != null) {
            blurEngine!!.toolbar = toolbar
        }
        blurEngine!!.blurRadius          = getBlurRadius()
        blurEngine!!.downScaleFactor     = getDownScaleFactor()
        blurEngine!!.shouldBlurToolbar   = shouldBlurToolbar()
        shouldDim                        = shouldDim()
    }

    override fun onStart() {
        dialog?.window?.let {
            if (shouldDim) {
                it.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            }
            val currentAnimation = it.attributes.windowAnimations
            if (currentAnimation == 0) {
                it.attributes.windowAnimations = R.style.BlurDialogFragment_Default_Animation
            }
        }
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        blurEngine?.onResume(retainInstance)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        blurEngine?.onDismiss()
    }

    override fun onDetach() {
        super.onDetach()
        blurEngine?.onDetach()
    }

    override fun onDestroyView() {
        dialog?.setDismissMessage(null)
        super.onDestroyView()
    }

    open fun setToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar
        blurEngine?.toolbar = toolbar
    }

    open fun getBlurRadius(): Int {
        return BlurDialogEngine.DEFAULT_BLUR_RADIUS
    }

    open fun shouldBlurToolbar(): Boolean {
        return BlurDialogEngine.DEFAULT_ACTION_BAR_BLUR
    }

    open fun getDownScaleFactor(): Float {
        return BlurDialogEngine.DEFAULT_BLUR_DOWNSCALE_FACTOR
    }

    open fun shouldDim(): Boolean {
        return BlurDialogEngine.DEFAULT_DIMMING_POLICY
    }
}
