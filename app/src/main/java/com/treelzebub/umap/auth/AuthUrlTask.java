package com.treelzebub.umap.auth;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.treelzebub.umap.api.discogs.Discogs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import retrofit.client.Response;

/**
 * Created by Tre Murillo on 3/29/15
 * <p/>
 * An AsyncTask that provides the authorization url for an access token request.
 */
public class AuthUrlTask extends AsyncTask<Context, Integer, String> {

    private Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(@NotNull Context... params) {
        mContext = params[0];
        try {
            return Discogs.getRequestToken();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        String responseStr = (response != null) ?
                        response : "Error processing request. Check your internet connection and try again.";
        Toast.makeText(mContext, responseStr, Toast.LENGTH_LONG).show();
    }
}
