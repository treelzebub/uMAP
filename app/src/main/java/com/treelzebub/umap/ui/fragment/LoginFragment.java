package com.treelzebub.umap.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.idiogram.umap.R;
import com.treelzebub.umap.Constants;
import com.treelzebub.umap.auth.AccessToken;
import com.treelzebub.umap.auth.LoginService;
import com.treelzebub.umap.util.ServiceGenerator;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Tre Murillo on 3/23/15
 */
public class LoginFragment extends Fragment {

    public static final String TAG = "LoginActivity";

    @InjectView(R.id.webview)
    WebView webView;
    @InjectView(R.id.auth_code_et)
    EditText authCodeET;
    @InjectView(R.id.submit_button)
    Button submitButton;

    @Override
    public void onResume() {
        super.onResume();

        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        Uri uri = getActivity().getIntent().getData();
        if (uri != null && uri.toString().startsWith(Constants.CALLBACK_URL)) {
            // use the parameter your API exposes for the code (mostly it's "code")
            String code = uri.getQueryParameter("code");
            if (code != null) {
                // get access token
                LoginService loginService =
                        ServiceGenerator.createService(
                                LoginService.class, Constants.BASE_URL,
                                Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
                AccessToken accessToken = loginService.getAccessToken(code, "authorization_code");

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

        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @SuppressWarnings("StatementWithEmptyBody")
                    @Override
                    public void onClick(View v) {
                        CharSequence authCode = authCodeET.getText();
                        if (authCode.length() > 0) {
                            Intent intent = new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(Constants.BASE_URL + "/login" + "?client_id=" + Constants.CONSUMER_KEY + "&redirect_uri=" + Constants.CALLBACK_URL));
                            startActivity(intent);
                            // GOAL!
                        } else {
                            Toast.makeText(getActivity(), "Please enter authorization code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return v;
    }
}
