package com.example.tabrezahmad.createresume;

import android.annotation.SuppressLint;
import android.app.Service;
import android.arch.persistence.room.Room;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.printservice.PrintService;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.tabrezahmad.createresume.database.MyRoomDatabase;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener {

    public static MyRoomDatabase mDatabase;     // room database

    public static Long BASIC_INFO_FOREIGN_KEY_ID = null;

    // ON CREATE ACTIVITY --------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // SET CONTENT LAYOUT
        setContentView(R.layout.activity_preview);

        // INIT DATABASE
        //setupDatabase();

        // INIT VIEWS
        setupView();

    }


    // DATABASE ------------------------------------------------------------------------------------
    private void setupDatabase() {

        // creating room database
        mDatabase = Room.databaseBuilder(getApplicationContext(), MyRoomDatabase.class, "mDatabase").build();

        Toast.makeText(this, "database created", Toast.LENGTH_SHORT).show();

    }


    // TOOLBAR AND FAB  ----------------------------------------------------------------------------
    private void setupFabAndToolbar() {

        // toolbar setup
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fab
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

    }


    // VIEW SETUP   --------------------------------------------------------------------------------
    private void setupView() {

        // TOOLBAR AND FAB SETUP
        setupFabAndToolbar();
        WebView wv_templates = findViewById(R.id.wv_templates);

        wv_templates.setWebChromeClient(new WebChromeClient());
        wv_templates.setWebViewClient(new WebViewClient());
        wv_templates.getSettings().setJavaScriptEnabled(true);              // enable javascript support
        wv_templates.getSettings().setDomStorageEnabled(true);              // for javascript to run
        wv_templates.getSettings().setSupportZoom(true);                    // zoomable
        wv_templates.getSettings().setAllowFileAccess(true);                // to acces external data, other than app assets

        //wv_templates.getSettings().setDefaultTextEncodingName("utf-8");     //
        {
            //wv_templates.setDrawingCacheBackgroundColor(Color.WHITE);
            //wv_templates.setDrawingCacheEnabled(true);
            //wv_templates.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);
            // 1. enable drawing cache
            // 2. draw drawing cache
            // 3. destroy drawing cache
            //wv_templates.destroyDrawingCache();
            //wv_templates.buildDrawingCache();
            //wv_templates.getDrawingCache();
            //wv_templates.destroyDrawingCache();
        }

        //JSObjectModel jsObjectModel = new JSObjectModel("Musharraf","jan 1, 2000");

        //wv_templates.addJavascriptInterface(jsObjectModel,"jObj");
        //wv_templates.loadUrl("file:///android_asset/template.html");
        wv_templates.loadUrl("javascript:alert('this is an alert');");


    }

    class MyChromeClient extends WebChromeClient{
        public MyChromeClient() {
            super();
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }
    }

    class MyWebClient extends WebViewClient {

        public MyWebClient() {
            super();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //view.loadUrl("javascript:alert('page loaded')");
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale) {
            super.onScaleChanged(view, oldScale, newScale);
        }
    }


    // BACK BUTTON PRESS    ------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    // OPTIONS MENU --------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // CLICK LISTENER    ---------------------------------------------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                    generatePdf();
                break;
        }
    }

    private void generatePdf() {
        @SuppressLint("ServiceCast") PrintService ps = (PrintService) getSystemService(Service.PRINT_SERVICE);
        Toast.makeText(this,"Resume Generated",Toast.LENGTH_SHORT).show();
    }


}

