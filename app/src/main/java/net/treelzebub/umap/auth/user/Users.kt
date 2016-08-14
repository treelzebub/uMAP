package net.treelzebub.umap.auth.user

import net.treelzebub.umap.data.Data
import net.treelzebub.umap.model.User

/**
 * Created by Tre Murillo on 8/6/16
 */
object Users {

    val user: User?       get() = Data.user.peek()
    val username: String? get() = user?.username

    fun hasUser() = user != null
}

class NoUserException(msg: String? = null) : RuntimeException(msg ?: "User Not Found.")