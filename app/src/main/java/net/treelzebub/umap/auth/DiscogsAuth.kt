package net.treelzebub.umap.auth

import org.scribe.model.Token

/**
 * Created by treelzebub on 9/22/2017
 */
object DiscogsAuth {

    private var token: Token? = null

    val isAuthed: Boolean
        get() = token == null

    fun init(token: Token) {
        this.token = token
    }
}