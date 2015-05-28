package com.treelzebub.umap.util

import android.util.Log

/**
 * Created by Tre Murillo on 5/28/15
 * Copyright(c) 2015 Level, Inc.
 */
public class BusProvider {
    private var BUS: UMapBus? = null

    public fun getInstance(): UMapBus {
        if (BUS == null) {
            Log.d("BusProvider", "Instantiate new provider")
            BUS = UMapBus()
        }
        return BUS
    }

    private fun BusProvider() {
    }
}