package com.treelzebub.umap.ui

import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.treelzebub.umap.R

/**
 * Created by Tre Murillo on 6/6/15
 * Copyright(c) 2015 Level, Inc.
 */
class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        tryToGetUserName()
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