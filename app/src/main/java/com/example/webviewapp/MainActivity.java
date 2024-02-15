package com.example.webviewapp;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity  {

    Button searchButton;
    EditText textInput;

    private WebView WebWinow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = findViewById(R.id.editText);
        searchButton = findViewById(R.id.button);
        WebWinow = findViewById(R.id.webView);
        WebWinow.setWebViewClient(new MyBrowser());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });


    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void search() {
        String query = textInput.getText().toString();
        if (!query.isEmpty()) {
            // Преобразует запрос в URL-адрес и загрузите его в WebView
            String searchUrl = "https://www.google.com/search?q=" + Uri.encode(query);

            WebWinow.getSettings().setLoadsImagesAutomatically(true);
            WebWinow.getSettings().setJavaScriptEnabled(true);
            WebWinow.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            WebWinow.loadUrl(searchUrl);
        } else {
            // Пустой запрос
            Toast.makeText(this, "Вы ничего не ввели для поиска", Toast.LENGTH_SHORT).show();
        }
    }
}