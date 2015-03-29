package com.treelzebub.umap.api;

/**
 * Created by Tre Murillo on 3/28/15
 */
public class AuthenticatedSession {
    private static String mUserName;

    private static boolean mIsLoggedIn = false;

    protected AuthenticatedSession() {
        //TODO stuff

//        mIsLoggedIn = response.getStatus() == 200;
    }

    public static String getUserName() {
        return mUserName;
    }

    public static void setUserName(String userName) {
        mUserName = userName;
    }

    private static boolean isLoggedIn() {
        return mIsLoggedIn;
    }
}
