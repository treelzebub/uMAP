package net.treelzebub.umap.activity

import android.content.Intent
import android.os.Bundle
import net.treelzebub.umap.activity.test.TestActivity

/**
 * Created by Tre Murillo on 8/17/15
 */
class MainActivity : UmapActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, TestActivity::class.java))
//        if (AuthState.isLoggedIn()) {
//            if (intent.data != null) {
//                handleData(intent.data)
//            }
//            startActivity(Intent(this, DashboardActivity::class.java))
//        } else {
//            startActivity(Intent(this, LoginActivity::class.java))
//        }
    }
}
