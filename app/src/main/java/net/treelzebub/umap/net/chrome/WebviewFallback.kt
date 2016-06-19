package net.treelzebub.umap.net.chrome

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * Created by Tre Murillo on 6/18/16
 */

class WebviewFallback : CustomTabActivityHelper.CustomTabFallback {

    override fun openUri(activity: Activity, uri: Uri) {
        val intent = Intent(activity, WebviewActivity::class.java)
        intent.putExtra(WebviewActivity.EXTRA_URL, uri.toString())
        activity.startActivity(intent)
    }
}
