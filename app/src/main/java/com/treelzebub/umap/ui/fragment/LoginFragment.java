package com.treelzebub.umap.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.treelzebub.umap.Constants;
import com.treelzebub.umap.R;
import com.treelzebub.umap.api.discogs.Discogs;
import com.treelzebub.umap.api.discogs.DiscogsConstants;
import com.treelzebub.umap.auth.AuthUrlTask;
import com.treelzebub.umap.util.BusProvider;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Tre Murillo on 3/23/15
 *
 * A fragment which will eventually provide a one-time login to Discogs (and optionally, Gemm)
 */
public class LoginFragment extends Fragment {

    public static final String TAG = "LoginActivity";

    private boolean hasAuthUrl = false;

    @InjectView(R.id.webview) WebView       mWebView;
    @InjectView(R.id.auth_code_et) EditText mAuthCodeET;
    @InjectView(R.id.submit_button) Button  mSubmitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BusProvider.getInstance().register(this);
        new AuthUrlTask().execute(new WeakReference<>(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        Uri uri = getActivity().getIntent().getData();
        if (uri != null && uri.toString().startsWith(Constants.CALLBACK_URL)) {
            // use the parameter your API exposes for the code (mostly it's "code")
            String code = uri.getQueryParameter("code");
            if (code != null) {
                // get access token
                String accessToken = new Discogs().getAccessToken(code);

            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
                Log.e("Redirect Error:", uri.getQueryParameter("error"));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_oauth, container, false);
        ButterKnife.inject(this, v);

        mSubmitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence authCode = mAuthCodeET.getText();
                        if (authCode.length() > 0) {
                            Intent intent = new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(DiscogsConstants.BASE_URL
                                            + "/login" + "?client_id=" + DiscogsConstants.CONSUMER_KEY
                                            + "&redirect_uri=" + Constants.CALLBACK_URL));
                            startActivity(intent);
                            // GOAL!
                        } else {
                            Toast.makeText(getActivity(), "Please enter authorization code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}
