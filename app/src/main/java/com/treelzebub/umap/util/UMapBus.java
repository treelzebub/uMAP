package com.treelzebub.umap.util;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by Tre Murillo on 5/16/15
 * Copyright(c) 2015 Level, Inc.
 */
public class UMapBus extends Bus {
    private final Handler mainThread = new Handler(Looper.getMainLooper());

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    post(event);
                }
            });
        }
    }

    @Override
    public void register(final Object object) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.register(object);
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    register(object);
                }
            });
        }
    }

    @Override
    public void unregister(final Object object) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            try {
                super.unregister(object);
            } catch (IllegalArgumentException e) {
                // dont care
            }
        } else {
            mainThread.post(new Runnable() {
                @Override
                public void run() {
                    unregister(object);
                }
            });
        }
    }
}
