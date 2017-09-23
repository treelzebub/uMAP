package net.treelzebub.umap.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.levelmoney.observefragment.activity.ObserveAppCompatActivity
import com.squareup.picasso.Picasso
import net.treelzebub.umap.R
import net.treelzebub.umap.activity.collection.CollectionActivity
import net.treelzebub.umap.data.Data
import net.treelzebub.umap.ui.CircleTransform
import net.treelzebub.umap.model.User
import net.treelzebub.umap.util.android.subscribeToBismarck
import net.treelzebub.umap.util.bus.BusProvider
import net.treelzebub.umap.util.kotlin.TAG
import org.jetbrains.anko.find
import org.jetbrains.anko.findOptional
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by Tre Murillo on 6/18/16
 */
open class UmapActivity : ObserveAppCompatActivity() {

    private val subscription = CompositeSubscription()

    var drawerToggle: ActionBarDrawerToggle? = null

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)

        subscribeToBismarck(Data.user) {
            setUser(it)
        }
    }

    override fun onResume() {
        super.onResume()
        setUser(Data.user.peek())
    }

    override fun onDestroy() {
        BusProvider.instance.unregister(this)
        super.onDestroy()
    }

    fun setupToolbar() {
        val toolbar = find<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setupDrawer() {
        val drawer = find<DrawerLayout>(R.id.drawer_layout)
        val toolbar = find<Toolbar>(R.id.toolbar)
        drawerToggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
        find<DrawerLayout>(R.id.drawer_layout).addDrawerListener(drawerToggle!!)
        drawerToggle!!.syncState()
        find<NavigationView>(R.id.navigation_view).setNavigationItemSelectedListener {
            it.isChecked = true
            drawer.closeDrawers()
            startActivity(Intent(this, CollectionActivity::class.java))
            true
        }
    }

    private fun setUser(user: User?) {
        val username = findOptional<TextView>(R.id.username)
        val name     = findOptional<TextView>(R.id.name)
        val avatar   = findOptional<ImageView>(R.id.avatar)
        user?.let {
            username?.text = it.username
            name?.text     = it.name
            if (avatar != null) {
                Picasso.with(this)
                       .load(it.avatarUrl)
                       .transform(CircleTransform())
                       .placeholder(R.drawable.icon)
                       .into(avatar)
            }
        }
    }


    fun <T> subscribe(observable: Observable<T>, fn: (T) -> Unit) {
        val sub = observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(fn) {
                    Log.e(TAG, "", it)
                }
        subscription.add(sub)
    }

    fun checkRelogin() {
        //TODO
    }
}
