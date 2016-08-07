package net.treelzebub.umap.sync

import android.content.Context
import net.treelzebub.umap.inject.ContextInjection
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

/**
 * Created by Tre Murillo on 10/11/15
 */

inline fun <reified T : Serializable> T.serialize(filename: String) {
    val c = ContextInjection.c
    val fileOut = c.openFileOutput(filename, Context.MODE_PRIVATE)
    val out = ObjectOutputStream(fileOut)
    try {
        out.writeObject(this)
    } catch(e: IOException) {
        e.printStackTrace()
    } finally {
        out.close()
        fileOut.close()
    }
}

object Files {

    inline fun <reified T : Serializable> deserialize(filename: String): T? {
        val c = ContextInjection.c
        try {
            c.openFileInput(filename).use { fileInput ->
                ObjectInputStream(fileInput).use { objectInput ->
                    return objectInput.readObject() as T
                }
            }
        } catch(e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}