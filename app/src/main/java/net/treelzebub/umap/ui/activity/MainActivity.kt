package net.treelzebub.umap.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.treelzebub.umap.auth.AuthState

/**
 * Created by Tre Murillo on 8/17/15
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        if (AuthState.isLoggedIn(this)) {
            startActivity(Intent(this, DashboardActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun init() {

    }
}
