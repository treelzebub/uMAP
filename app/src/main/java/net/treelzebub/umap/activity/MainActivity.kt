package net.treelzebub.umap.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import net.treelzebub.umap.activity.dashboard.DashboardActivity
import net.treelzebub.umap.activity.login.LoginActivity
import net.treelzebub.umap.auth.AuthState

/**
 * Created by Tre Murillo on 8/17/15
 */
class MainActivity : UmapActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AuthState.isLoggedIn()) {
            if (intent.data != null) {
                handleData(intent.data)
            }
            startActivity(Intent(this, DashboardActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    private fun handleData(uri: Uri) {
        for (s in uri.pathSegments) {
            when (s) {
                "release" -> {}


                else -> {}
            }
        }
    }
}
