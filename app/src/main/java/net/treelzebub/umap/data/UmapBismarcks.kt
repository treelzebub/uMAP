package net.treelzebub.umap.data

import android.content.Context
import com.levelmoney.bismarck.Bismarcks
import com.levelmoney.bismarck.fetcher
import net.treelzebub.umap.api.Discogs
import net.treelzebub.umap.api.model.Collection
import net.treelzebub.umap.api.model.User
import net.treelzebub.umap.auth.user.Users
import net.treelzebub.umap.bismarck.discogs

/**
 * Created by Tre Murillo on 8/6/16.
 */
object UmapBismarcks {

    fun register(c: Context) {

        Data.user = Bismarcks.discogs<User>("discogs_user")
                .fetcher {
                    Discogs.connect {
                        getUser(Users.username)
                    }
                }

        Data.collection = Bismarcks.discogs<Collection>("discogs_user_collection")
                .fetcher {
                    Discogs.connect {
                        getCollection(Users.username)
                    }
                }
    }
}
