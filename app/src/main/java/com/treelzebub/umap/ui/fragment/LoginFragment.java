package com.treelzebub.umap.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.idiogram.umap.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Tre Murillo on 3/23/15
 */
public class LoginFragment extends Fragment {

    public static final String TAG = "LoginActivity";

    public boolean mHasLoggedIn = false;

    @InjectView(R.id.webview)
    WebView webView;
    @InjectView(R.id.auth_code_et)
    EditText authCodeET;
    @InjectView(R.id.submit_button)
    Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_oauth, container, false);
        ButterKnife.inject(this, v);

        webView.loadUrl("http://www.asdf.com");
        submitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence authCode = authCodeET.getText();
                        if (authCode.length() > 0) {
                            // GOAL!
                        } else {
                            Toast.makeText(getActivity(), "Please enter authorization code", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return v;
    }
}
