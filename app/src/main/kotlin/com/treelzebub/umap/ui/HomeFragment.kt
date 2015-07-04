package com.treelzebub.umap.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.squareup.otto.Subscribe
import com.treelzebub.umap.R
import com.treelzebub.umap.async.event.UserEvent
import com.treelzebub.umap.util.BusProvider

/**
 * Created by Tre Murillo on 6/6/15
 */
public class HomeFragment : Fragment() {

    val tokenText: TextView by bindView(R.id.token_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BusProvider.instance.register(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        BusProvider.instance.unregister(this)
    }

    Subscribe
    public fun setUserNameTextView(event: UserEvent) {
        tokenText.setText(event.user.username)
    }
}