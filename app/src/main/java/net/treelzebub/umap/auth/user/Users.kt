package net.treelzebub.umap.auth.user

import net.treelzebub.umap.R
import net.treelzebub.umap.inject.ContextInjection

/**
 * Created by Tre Murillo on 8/6/16.
 */
object Users {

    val accountType: String
        get() = ContextInjection.c.getString(R.string.account_type)
}
