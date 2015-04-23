package com.treelzebub.umap.auth;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.treelzebub.umap.api.discogs.Discogs;

import org.jetbrains.annotations.NotNull;

import retrofit.client.Response;

/**
 * Created by Tre Murillo on 3/29/15
 * <p/>
 * An AsyncTask that provides the authorization url for an access token request.
 */
public class AuthUrlTask extends AsyncTask<Context, Integer, Response> {

    private Discogs mDiscogs;
    private Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDiscogs = Discogs.getInstance();
    }

    @Override
    protected Response doInBackground(@NotNull Context... params) {
        mContext = params[0];
        try {
            return mDiscogs.getRequestToken();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        String responseStr =
                (response != null) ?
                        response.toString() :
                        "Error processing request. Check your internet connection and try again.";
        Toast.makeText(mContext, responseStr, Toast.LENGTH_LONG).show();
    }
}
