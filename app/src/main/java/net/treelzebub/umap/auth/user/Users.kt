package net.treelzebub.umap.auth.user

import net.treelzebub.umap.R
import net.treelzebub.umap.api.model.User
import net.treelzebub.umap.inject.ContextInjection
import net.treelzebub.umap.sync.Files

/**
 * Created by Tre Murillo on 8/6/16.
 */
object Users {

    val accountType: String
        get() = ContextInjection.c.getString(R.string.account_type)

    var user: User? = null

    val username: String get() = user!!.username

    fun deserializeUser(): User? {
        return Files.deserialize<User>("user.umap")
    }
}

class NoUserException(msg: String? = null) : RuntimeException(msg ?: "User Not Found.")