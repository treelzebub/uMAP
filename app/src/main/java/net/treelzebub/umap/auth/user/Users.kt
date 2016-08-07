package net.treelzebub.umap.auth.user

import net.treelzebub.umap.R
import net.treelzebub.umap.api.model.User
import net.treelzebub.umap.data.Data
import net.treelzebub.umap.inject.ContextInjection

/**
 * Created by Tre Murillo on 8/6/16.
 */
object Users {

    val accountType: String
        get() = ContextInjection.c.getString(R.string.account_type)

    val user: User? get () = Data.user.peek()

    val username: String? get() = user?.username

    fun hasUser(): Boolean {
        return Data.user.peek()?.username != null
    }
}

class NoUserException(msg: String? = null) : RuntimeException(msg ?: "User Not Found.")