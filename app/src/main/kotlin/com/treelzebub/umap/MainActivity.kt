package com.treelzebub.umap

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.treelzebub.umap.ui.DashboardActivity
import com.treelzebub.umap.ui.LoginActivity
import com.treelzebub.umap.util.UserUtils

/**
 * Created by Tre Murillo on 8/17/15
 */
public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (UserUtils.isLoggedIn(this)) {
            startActivity(Intent(this, javaClass<DashboardActivity>()))
        } else {
            startActivity(Intent(this, javaClass<LoginActivity>()))
        }
    }
}