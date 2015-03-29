package com.treelzebub.umap.api;

import retrofit.client.Response;

/**
 * Created by Tre Murillo on 3/28/15
 */
public class AuthenticatedSession {
    private String mUserName;

    private boolean mIsLoggedIn = false;

    protected int Discogs(Response response) {
        //TODO stuff

        mIsLoggedIn = !(response == null) && !(response.getBody() == null);

        return response.getStatus();
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    private boolean isLoggedIn() {
        return mIsLoggedIn;
    }
}
