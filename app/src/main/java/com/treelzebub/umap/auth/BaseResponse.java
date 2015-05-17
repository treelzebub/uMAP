package com.treelzebub.umap.auth;

/**
 * Created by Tre Murillo on 3/24/15
 */
public abstract class BaseResponse {
    private String error = null;

    public String getError() {
        return error;
    }
}