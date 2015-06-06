package com.treelzebub.umap.ui

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.treelzebub.umap.R
import com.treelzebub.umap.util.getPrefs

/**
 * Created by Tre Murillo on 6/6/15
 */
class HomeFragment : Fragment() {

    val tokenText: TextView by bindView(R.id.token_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tryToGetUserName()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val tokenString = getPrefs(getActivity())?.getString(getString(R.string.key_access_token), null)
        tokenText.setText(tokenString)
        return v
    }

    private fun tryToGetUserName() {
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                //retrofit request for username
                return null
            }
        }
    }


}