package com.treelzebub.umap.util.BusProvider

import android.util.Log
import com.treelzebub.umap.util.UMapBus

/**
 * Created by Tre Murillo on 5/28/15
 */
private var BUS: UMapBus? = null

fun getInstance(): UMapBus {
    if (BUS == null) {
        Log.d("BusProvider", "Instantiate new provider")
        BUS = UMapBus()
    }
    return BUS!!
}

private fun BusProvider() {
}