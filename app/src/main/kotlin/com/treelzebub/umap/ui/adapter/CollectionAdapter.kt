package com.treelzebub.umap.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.treelzebub.umap.R
import com.treelzebub.umap.api.discogs.model.CollectionFolder
import butterknife.bindView


/**
 * Created by Tre Murillo on 6/7/15
 * Copyright(c) 2015 Level, Inc.
 */
public class CollectionAdapter : RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {

    var folders: List<CollectionFolder> = listOf()

    val albumCover: ImageView by bindView(R.id.cardview_album_cover)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return folders.size()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    private inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    }
}