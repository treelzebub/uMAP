package com.treelzebub.umap.util

import android.content.Context
import android.content.SharedPreferences
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.model.User
import net.sarazan.prefs.Pref
import net.sarazan.prefs.SharedPref
import java.io.File
import kotlin.platform.platformStatic

/**
 * Created by Tre Murillo on 6/5/15
 */

fun getPrefs(c: Context): SharedPreferences? {
    return c.getApplicationContext().getSharedPreferences(c.getString(R.string.key_pref_file), Context.MODE_PRIVATE)
}

fun clearPrefs(c: Context): Boolean {
    getPrefs(c)!!.edit().clear().commit()
    val root = c.getFilesDir() ?: return false
    val dir = File(root.getParent() + "/shared_prefs/")
    val xml = File(dir, c.getString(R.string.key_pref_file) + ".xml")
    return xml.delete()
}
