package com.treelzebub.umap.util

import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus

/**
 * Created by Tre Murillo on 5/28/15
 * Copyright(c) 2015 Level, Inc.
 */
public class UMapBus : Bus() {
private val mainThread = Handler(Looper.getMainLooper())

override fun post(event: Any) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        super.post(event)
    } else {
        mainThread.post(object : Runnable {
            override fun run() {
                post(event)
            }
        })
    }
}

override fun register(any: Any) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        super.register(any)
    } else {
        mainThread.post(object : Runnable {
            override fun run() {
                register(any)
            }
        })
    }
}

override fun unregister(any: Any) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        try {
            super.unregister(any)
        } catch (e: IllegalArgumentException) {
            // dont care
        }

    } else {
        mainThread.post(object : Runnable {
            override fun run() {
                unregister(any)
            }
        })
    }
}
}
