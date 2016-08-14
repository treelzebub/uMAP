package net.treelzebub.umap.util.android

import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by Tre Murillo on 8/7/16
 */

val ViewGroup.inflater: LayoutInflater get() = LayoutInflater.from(context)