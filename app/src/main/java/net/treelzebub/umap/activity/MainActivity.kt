package net.treelzebub.umap.activity

import android.content.Intent
import android.os.Bundle
import net.treelzebub.umap.activity.collection.CollectionActivity
import net.treelzebub.umap.activity.login.LoginActivity
import net.treelzebub.umap.auth.AuthState

/**
 * Created by Tre Murillo on 8/17/15
 */
class MainActivity : UmapActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (AuthState.isLoggedIn()) {
            startActivity(Intent(this, CollectionActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
