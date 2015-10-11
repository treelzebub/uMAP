package net.treelzebub.umap.util

import android.os.AsyncTask

/**
 * Created by Tre Murillo on 10/5/15
 */
public fun async(fn: () -> Unit, post: (() -> Unit)? = null) {
    object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            fn()
            return null
        }

        override fun onPostExecute(result: Void?) {
            if (post != null) post()
        }
    }.execute()
}
