package net.treelzebub.umap.activity

import android.annotation.SuppressLint
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_collection.*
import kotlinx.android.synthetic.main.drawer_header.*
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.collection.CollectionActivity
import net.treelzebub.umap.auth.Users
import net.treelzebub.umap.model.User
import net.treelzebub.umap.ui.CircleTransform
import net.treelzebub.umap.util.TAG
import net.treelzebub.umap.util.android.onLayoutChange
import net.treelzebub.umap.util.rx.umap
import org.jetbrains.anko.startActivity

/**
 * Created by Tre Murillo on 6/18/16
 */
@SuppressLint("Registered")
open class UmapActivity : AppCompatActivity() {

    private var subscription = CompositeDisposable()

    protected var drawerToggle: ActionBarDrawerToggle? = null

    override fun onResume() {
        super.onResume()
        if (subscription.isDisposed) {
            subscription = CompositeDisposable()
        }
    }

    override fun onDestroy() {
        subscription.dispose()
        super.onDestroy()
    }

    protected fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar) ?: return
        setSupportActionBar(toolbar)
        supportActionBar!!.apply {
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun setupDrawer() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout) ?: return
        drawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
        drawer.addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()
        navigation_view.apply {
            setNavigationItemSelectedListener {
                it.isChecked = true
                drawer.closeDrawers()
                startActivity<CollectionActivity>()
                true
            }
            if (Users.isLoggedIn()) {
                setUser(Users.user!!, this)
            }
        }
    }

    private fun setUser(user: User, nav: NavigationView) {
        nav.onLayoutChange {
            username.text = user.username
            name.text = user.name
            Picasso.with(this@UmapActivity)
                    .load(user.avatarUrl)
                    .transform(CircleTransform())
                    .placeholder(R.drawable.icon)
                    .into(avatar)
        }
    }

    fun <T> subscribe(observable: Observable<T>, fn: (T) -> Unit) {
        val sub = observable.umap()
                .subscribe(fn) {
                    Log.e(TAG, "", it)
                }
        subscription.add(sub)
    }
}