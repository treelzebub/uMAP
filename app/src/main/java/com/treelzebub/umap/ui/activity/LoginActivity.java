package com.treelzebub.umap.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.idiogram.umap.R;

/**
 * Created by Tre Murillo on 3/23/15
 */
public class LoginActivity extends Activity {

    public static final String TAG = "LoginActivity";

    public boolean mHasLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (!mHasLoggedIn) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_oauth, null);
            dialogBuilder.setView(dialogView);

            WebView webView = (WebView) dialogView.findViewById(R.id.webview);
            EditText authCodeET = (EditText) dialogView.findViewById(R.id.auth_code_et);
            Button submitButton = (Button) dialogView.findViewById(R.id.submit_button);

            webView.loadUrl("http://www.asdf.com");
        }

    }
}
