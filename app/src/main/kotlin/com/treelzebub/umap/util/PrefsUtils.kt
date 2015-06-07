package com.treelzebub.umap.util

import android.content.Context
import android.content.SharedPreferences
import com.treelzebub.umap.R
import net.sarazan.prefs.Pref
import net.sarazan.prefs.SharedPref
import kotlin.platform.platformStatic

/**
 * Created by Tre Murillo on 6/5/15
 */
var prefs: SharedPreferences? = null

fun getPrefs(c: Context): SharedPreferences? {
    return c.getApplicationContext().getSharedPreferences(c.getString(R.string.key_pref_file), Context.MODE_PRIVATE)
}

fun clearPrefs(c: Context) {
    getPrefs(c)?.edit()?.clear()?.commit()
}