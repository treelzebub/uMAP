package net.treelzebub.umap.activity

import android.os.Bundle
import net.treelzebub.umap.activity.collection.CollectionActivity
import net.treelzebub.umap.activity.login.LoginActivity
import net.treelzebub.umap.auth.Users
import org.jetbrains.anko.startActivity

/**
 * Created by Tre Murillo on 8/17/15
 */
class MainActivity : UmapActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Users.load(this)) {
            startActivity<CollectionActivity>()
        } else {
            startActivity<LoginActivity>()
        }
    }
}
