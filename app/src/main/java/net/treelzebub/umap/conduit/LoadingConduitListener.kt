package net.treelzebub.umap.conduit

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import com.levelmoney.conduit.listener.DialogListener
import net.treelzebub.umap.R
import net.treelzebub.umap.ui.loadinggiffragment.gif.LoadingGifFragment


/**
 * Created by Tre Murillo on 8/7/16.
 */
class LoadingConduitListener<D> : DialogListener<D, LoadingGifFragment> {

    constructor(fragment: Fragment) : super(fragment, LoadingGifFragment::class.java)
    constructor(activity: FragmentActivity) : super(activity, LoadingGifFragment::class.java)

    override fun onDialogFragmentCreated(dialogFragment: DialogFragment?) {
        val c = conduit.getActivity()
        if (dialogFragment is LoadingGifFragment) {
            dialogFragment.setBackgroundColor(ContextCompat.getColor(c, R.color.accent_translucent))
        }


    }
}
