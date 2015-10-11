package net.treelzebub.umap.util

import android.os.AsyncTask

/**
 * Created by Tre Murillo on 10/5/15
 */
public fun async(fn: () -> Unit) {
    object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            fn()
            return null
        }
    }.execute(null)
}

public fun async(fn: () -> Unit, post: () -> Unit) {
    object : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void): Void? {
            fn()
            return null
        }

        override fun onPostExecute(result: Void?) {
            post()
        }
    }.execute(null)
}
