package net.treelzebub.umap.auth.user

import net.treelzebub.umap.R
import net.treelzebub.umap.api.DiscogsService
import net.treelzebub.umap.api.model.User
import net.treelzebub.umap.inject.ContextInjection
import net.treelzebub.umap.sync.Files
import net.treelzebub.umap.sync.serialize
import net.treelzebub.umap.util.android.async

/**
 * Created by Tre Murillo on 8/6/16.
 */
object Users {

    val accountType: String
        get() = ContextInjection.c.getString(R.string.account_type)

    var user: User? = null
        get() = field ?: throw NoUserException()
        set(value) {
            field = value
            serializeUser(value)
        }

    val username: String get() = user!!.username

    fun serializeUser(user: User? = null) {
        async() {
            val user = user ?: DiscogsService.getUser()
            user.serialize("user.umap")
        }
    }

    fun deserializeUser(): User? {
        return Files.deserialize<User>("user.umap")
    }
}

class NoUserException(msg: String? = null) : RuntimeException(msg ?: "User Not Found.")