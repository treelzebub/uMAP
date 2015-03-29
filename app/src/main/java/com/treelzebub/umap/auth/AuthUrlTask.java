package com.treelzebub.umap.auth;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.treelzebub.umap.api.discogs.Discogs;

/**
 * Created by Tre Murillo on 3/29/15
 */
public class AuthUrlTask extends AsyncTask<Context, Integer, String> {

    private Discogs mDiscogs;
    private Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDiscogs = Discogs.getInstance();
    }

    @Override
    protected String doInBackground(Context... params) {
        mContext = params[0];
        try {
            return mDiscogs.getRequestToken().getBody().toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);

        if (str == null) str = "Error processing your request. Is internet available?";

        Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
    }
}
