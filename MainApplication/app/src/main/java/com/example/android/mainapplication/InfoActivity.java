package com.example.android.mainapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class InfoActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        webview = (WebView) findViewById(R.id.webViewForInfo);

        StringBuilder sb = new StringBuilder();

        //File
        try {
            InputStream is = getAssets().open("index.html");
            int data;
            while (true) {
                data = is.read();
                if (data < 0)
                    break;
                sb.append((char) data);
            }
        } catch (IOException e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }

        Uri path = Uri.parse("file:///android_asset/assets");
        webview.loadDataWithBaseURL(path.toString(), sb.toString(), "text/html", null, null);
    }

}
