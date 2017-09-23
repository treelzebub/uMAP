package net.treelzebub.umap.util.android

import android.content.Context

import java.io.*
/**
 * Created by treelzebub on 9/23/2017
 */

fun Context.persist(filename: String, bytes: ByteArray) {
    openFileOutput(filename, Context.MODE_PRIVATE).use {
        it.write(bytes)
    }
}

fun Serializable.persist(context: Context, filename: String) {
    context.persist(filename, this.bytes())
}

fun <T : Serializable> Context.loadFile(filename: String): T? {
    return openFileInput(filename).use {
        it.readBytes().deserialize()
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