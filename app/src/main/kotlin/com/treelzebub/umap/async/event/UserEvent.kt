package com.treelzebub.umap.async.event

import com.treelzebub.umap.api.discogs.model.User

/**
 * Created by Tre Murillo on 6/6/15
 */
public class UserEvent {
    var user: User = User()

    constructor(user: User?) {
        this.user = user!!
    }
}