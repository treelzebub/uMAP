package net.treelzebub.umap.net.chrome

import android.app.Activity
import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import net.treelzebub.umap.R
import net.treelzebub.umap.util.resolveColor

/**
 * Created by Tre Murillo on 6/18/16
 */

object CustomTabs {

    fun open(a: Activity, url: String) {
        val intent = CustomTabsIntent.Builder()
                        .setToolbarColor(a.resolveColor(R.color.primaryColorDark))
                        .setSecondaryToolbarColor(a.resolveColor(R.color.primaryColor))
                        .build()
        CustomTabActivityHelper.openCustomTab(a, intent, Uri.parse(url), WebviewFallback())
    }
}

fun Activity.openUrl(url: String) = CustomTabs.open(this, url)
