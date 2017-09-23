package net.treelzebub.umap.util.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Tre Murillo on 8/7/16
 */

val ViewGroup.inflater: LayoutInflater get() = LayoutInflater.from(context)

fun View.onLayoutChange(fn: (View) -> Unit) {
   addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
       override fun onLayoutChange(view: View, p1: Int, p2: Int, p3: Int, p4: Int, p5: Int, p6: Int, p7: Int, p8: Int) {
           removeOnLayoutChangeListener(this)
           fn(view)
       }
   })
}