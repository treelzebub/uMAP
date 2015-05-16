package com.treelzebub.umap.auth;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.treelzebub.umap.api.discogs.Discogs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;

import retrofit.client.Response;

/**
 * Created by Tre Murillo on 3/29/15
 * <p/>
 * An AsyncTask that provides the authorization url for an access token request.
 */
public class AuthUrlTask extends AsyncTask<WeakReference<Activity>, Integer, String> {

    private WeakReference<Activity> mWeakActivity;
    private Discogs mDiscogs;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDiscogs = new Discogs();
    }

    @Override
    protected String doInBackground(@NotNull WeakReference... params) {
        mWeakActivity = params[0];
        try {
            return mDiscogs.getRequestToken();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String url) {
        super.onPostExecute(url);
        Toast.makeText(mWeakActivity.get().getApplicationContext(), url, Toast.LENGTH_LONG).show();
        //TODO webview bullshit or xauth slightly-less-bullshit
    }
}
