package com.treelzebub.umap.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.treelzebub.umap.R;
import com.treelzebub.umap.api.discogs.DiscogsConstants;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Tre Murillo on 3/23/15
 * <p/>
 * A fragment which will eventually provide a one-time login to Discogs (and optionally, Gemm)
 */
public class LoginFragment extends Fragment {

    public static final String TAG = "LoginActivity";

    private boolean hasAuthUrl = false;

    @InjectView(R.id.webview) WebView        mWebView;
    @InjectView(R.id.auth_code_et) EditText  mAuthCodeET;
    @InjectView(R.id.submit_button) Button   mSubmitButton;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_oauth, container, false);
        ButterKnife.inject(this, v);
        return v;
    }

    @OnClick(R.id.submit_button)
    protected void submit(View v) {
        CharSequence authCode = mAuthCodeET.getText();
        if (authCode.length() > 0) {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(DiscogsConstants.BASE_URL + "/login" + "?client_id=" + DiscogsConstants.CONSUMER_KEY + "&redirect_uri=" + DiscogsConstants.CALLBACK_URL));
            startActivity(intent);
            // GOAL!
        } else {
            Toast.makeText(getActivity(), "Please enter authorization code", Toast.LENGTH_SHORT).show();
        }
    }
}
