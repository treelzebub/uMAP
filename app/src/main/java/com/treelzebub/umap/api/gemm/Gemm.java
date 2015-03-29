package com.treelzebub.umap.api.gemm;

import com.treelzebub.umap.api.AuthenticatedSession;

/**
 * Created by Tre Murillo on 3/28/15
 *
 * A class representing an authenticated Gemm session.
 *
 * Upon successful login, this class feeds the UnifiedCollection with details from the user's
 * gemm.com db; and feeds the UnifiedMarketplace information from gemm.com's market db. */

 public class Gemm extends AuthenticatedSession {

    private static Gemm instance;
    public static Gemm getInstance() {
        return instance == null ? instance = new Gemm() : instance;
    }
}
