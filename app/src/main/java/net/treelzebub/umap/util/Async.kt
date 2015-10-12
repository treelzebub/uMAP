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

public fun <T> async(fn: () -> T, post: (result: T) -> Unit) {
    object : AsyncTask<Void, Void, T>() {
        override fun doInBackground(vararg params: Void): T {
            return fn()
        }

        override fun onPostExecute(result: T) {
            post(result)
        }
    }.execute(null)
}
