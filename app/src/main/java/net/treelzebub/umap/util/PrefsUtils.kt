package net.treelzebub.umap.util

import android.content.Context
import android.content.SharedPreferences
import net.sarazan.prefs.Pref
import net.sarazan.prefs.SharedPref
import net.treelzebub.umap.R
import java.io.File

/**
 * Created by Tre Murillo on 6/5/15
 */
object PrefsUtils {

    fun getPrefs(c: Context): SharedPreferences? {
        return c.applicationContext.getSharedPreferences(c.getString(R.string.key_pref_file), Context.MODE_PRIVATE)
    }

    fun clearPrefs(c: Context): Boolean {
        getPrefs(c)?.edit()?.clear()?.commit()
        val root = c.filesDir ?: return false
        val dir = File(root.getParent() + "/shared_prefs/")
        val xml = File(dir, c.getString(R.string.key_pref_file) + ".xml")
        return xml.delete()
    }

    fun <T> userPref(key: String, clazz: Class<T>): Pref<T> {
        return object : SharedPref<T>(key, clazz) {
            override fun getSharedPreferences(c: Context): SharedPreferences? {
                return getPrefs(c)
            }
        }
    }
}
