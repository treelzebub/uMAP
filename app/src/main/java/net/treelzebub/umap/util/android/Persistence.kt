package net.treelzebub.umap.util.android

import android.content.Context
import org.jetbrains.anko.doAsync

import java.io.*


/**
 * Created by treelzebub on 9/23/2017
 */

fun Serializable.persist(context: Context, filename: String) {
    context.persist(filename, this.bytes())
}

fun <T : Serializable> Context.loadFile(filename: String): T? {
    return openFileInput(filename).use {
        it.readBytes().deserialize()
    }
}

fun Serializable.persistAsync(context: Context, filename: String)
    = doAsync { persist(context, filename) }

fun <T : Serializable> Context.loadFileAsync(filename: String)
    = doAsync { loadFile<T>(filename) }

private fun Context.persist(filename: String, bytes: ByteArray) {
    openFileOutput(filename, Context.MODE_PRIVATE).use {
        it.write(bytes)
    }
}

private fun Serializable.bytes(): ByteArray {
    return ByteArrayOutputStream().use {
        ObjectOutputStream(it).use { it.writeObject(this) }
        it.toByteArray()
    }
}

@Suppress("UNCHECKED_CAST")
private fun <T> ByteArray.deserialize(): T? {
    return ByteArrayInputStream(this).use {
        ObjectInputStream(it).use { it.readObject() as T? }
    }
}