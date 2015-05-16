package com.treelzebub.umap.util;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Tre Murillo on 5/16/15
 * Copyright(c) 2015
 */
public class BusProvider {
    private static UMapBus BUS;

    @NotNull
    public static UMapBus getInstance() {
        if (BUS == null) {
            Log.d("BusProvider", "Instantiate new provider");
            BUS = new UMapBus();
        }
        return BUS;
    }

    private BusProvider() {
    }
}
