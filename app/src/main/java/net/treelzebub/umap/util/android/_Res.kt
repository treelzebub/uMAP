package net.treelzebub.umap.util.android

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import net.treelzebub.umap.inject.ContextInjection

/**
 * Created by Tre Murillo on 6/18/16
 */

val c: Context get() = ContextInjection.c

fun Int.color(): Int          = ContextCompat.getColor(c, this)
fun Int.str(): String         = c.getString(this)
fun Int.dimen(): Float        = c.resources.getDimension(this)
fun Int.drawable(): Drawable  = ContextCompat.getDrawable(c, this)

fun Context.resolveColor(colorAttribute: Int): Int {
    val typedValue = TypedValue()
    val a = obtainStyledAttributes(typedValue.data, intArrayOf(colorAttribute))
    val color = a.getColor(0, 0)
    a.recycle()
    return color
}

fun Activity.resolve(attr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.resourceId
}