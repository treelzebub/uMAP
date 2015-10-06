package com.treelzebub.umap.util

import android.content.Context
import android.content.SharedPreferences
import com.treelzebub.umap.R
import net.sarazan.prefs.Pref
import net.sarazan.prefs.SharedPref
import java.io.File

/**
 * Created by Tre Murillo on 6/5/15
 */
public fun getPrefs(c: Context): SharedPreferences? {
    return c.applicationContext.getSharedPreferences(c.getString(R.string.key_pref_file), Context.MODE_PRIVATE)
}

public fun clearPrefs(c: Context): Boolean {
    getPrefs(c)?.edit()?.clear()?.commit()
    val root = c.filesDir ?: return false
    val dir = File(root.getParent() + "/shared_prefs/")
    val xml = File(dir, c.getString(R.string.key_pref_file) + ".xml")
    return xml.delete()
}

public fun <T> userPref(key: String, clazz: Class<T>): Pref<T> {
    return object : SharedPref<T>(key, clazz) {
        override fun getSharedPreferences(c: Context): SharedPreferences? {
            return getPrefs(c)
        }
    }
}
