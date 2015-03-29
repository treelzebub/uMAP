package com.treelzebub.umap.auth;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.treelzebub.umap.api.AuthTools;
import com.treelzebub.umap.api.discogs.DiscogsApi;
import com.treelzebub.umap.api.discogs.DiscogsConstants;
import com.treelzebub.umap.util.ServiceGenerator;

/**
 * Created by Tre Murillo on 3/29/15
 */
public class AuthUrlTask extends AsyncTask<Context, Integer, String> {

    private DiscogsApi mDiscogsService;
    private Context mContext;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDiscogsService = ServiceGenerator.createService(
                DiscogsApi.class, DiscogsConstants.BASE_URL,
                DiscogsConstants.CONSUMER_KEY, AuthTools.getNonce(),
                DiscogsConstants.CONSUMER_SECRET, DiscogsConstants.SIGNATURE_METHOD,
                AuthTools.getTimestamp(), DiscogsConstants.CALLBACK_URL);
    }

    @Override
    protected String doInBackground(Context... params) {
        mContext = params[0];

        String authUrl = mDiscogsService.getAuthUrl();

        return authUrl;

    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);

        Toast.makeText(mContext, str, Toast.LENGTH_LONG).show();
    }
}
