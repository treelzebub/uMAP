package net.treelzebub.umap.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.treelzebub.umap.auth.AuthState

/**
 * Created by Tre Murillo on 8/17/15
 */
public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AuthState.isLoggedIn()) {
            startActivity(Intent(this, DashboardActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
