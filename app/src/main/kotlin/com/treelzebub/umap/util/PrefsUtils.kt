package com.treelzebub.umap.util

import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.StringRes
import com.treelzebub.umap.R
import net.sarazan.prefs.Pref
import net.sarazan.prefs.SharedPref
import java.io.File

/**
 * Created by Tre Murillo on 6/5/15
 */
public fun getPrefs(c: Context): SharedPreferences? {
    return c.getApplicationContext().getSharedPreferences(c.getString(R.string.key_pref_file), Context.MODE_PRIVATE)
}

public fun clearPrefs(c: Context): Boolean {
    getPrefs(c)!!.edit().clear().commit()
    val root = c.getFilesDir() ?: return false
    val dir = File(root.getParent() + "/shared_prefs/")
    val xml = File(dir, c.getString(R.string.key_pref_file) + ".xml")
    return xml.delete()
}
