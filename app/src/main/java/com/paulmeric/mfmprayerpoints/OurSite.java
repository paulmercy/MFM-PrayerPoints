package com.paulmeric.mfmprayerpoints;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class OurSite extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.paulmercy.mfmhymns.MESSAGE";
    private static final String TAG = "WebscreenClass";
    public static final String URL = "";
    String newli;
    private ProgressDialog progressBar;
    private ProgressDialog progressBar2;
    private ProgressDialog progressDialog;
    ScrollView refresh;
    String turl;
    WebView webview;
    RelativeLayout ww;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        if (savedInstanceState != null) {
            this.webview.restoreState(savedInstanceState);
        } else {
            somethingToDo();
        }
    }

    @SuppressLint("WrongConstant")
    private void somethingToDo() {
        this.turl = getIntent().getStringExtra(URL);
        Log.i(TAG, " URL = " + this.turl);
        this.webview = findViewById(R.id.webview);
        this.refresh = findViewById(R.id.refresh);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.getSettings().setBuiltInZoomControls(true);
        this.webview.getSettings().setDisplayZoomControls(false);
        this.webview.getSettings().setAllowFileAccess(true);
        this.webview.setScrollBarStyle(33554432);
        this.webview.getSettings().setLoadWithOverviewMode(true);
        this.webview.getSettings().setUseWideViewPort(true);
        this.webview.setScrollbarFadingEnabled(true);
        this.webview.getSettings().setLoadsImagesAutomatically(true);
        if (isOnline(this)) {
            this.refresh.setVisibility(8);
            this.webview.setVisibility(0);
            final AlertDialog alertDialog = new Builder(this).create();
            this.progressBar = ProgressDialog.show(OurSite.this,"Loading...","Loading, please wait...",false,false);
            progressBar.setCancelable(true);
            progressBar.setCanceledOnTouchOutside(true);
            progressBar.setProgressStyle(8);
            this.webview.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    Log.i(OurSite.TAG, "Processing webview url click...");
                    view.loadUrl(url);
                    return true;
                }

                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    OurSite.this.progressBar.show();
                    super.onPageStarted(view, url, favicon);
                }

                public void onPageFinished(WebView view, String url) {
                    Log.i(OurSite.TAG, "Finished loading URL: " + OurSite.this.turl);
                    if (OurSite.this.progressBar.isShowing()) {
                        OurSite.this.progressBar.dismiss();
                    }
                }

                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Log.e(OurSite.TAG, "Error: " + description);
                    Toast.makeText(OurSite.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage(description);
                    alertDialog.setButton("OK", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                }
            });
            this.webview.loadUrl(this.turl);
            return;
        }
        this.webview.setVisibility(8);
        Toast.makeText(this, "No network connection. Please switch on your Mobile Data or WIFI service ", Toast.LENGTH_SHORT).show();
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        this.webview.saveState(outState);
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.webview.restoreState(savedInstanceState);
    }

    private boolean isOnline(Context mContext) {
        @SuppressLint("WrongConstant") NetworkInfo netInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.webview.canGoBack()) {
            return super.onKeyDown(keyCode, event);
        }
        this.webview.goBack();
        return true;
    }

    @SuppressLint("WrongConstant")
    private void goBax() {
        if (this.webview.canGoBack()) {
            this.webview.goBack();
        } else {
            Toast.makeText(getBaseContext(), "No existing link", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("WrongConstant")
    private void goFax() {
        if (this.webview.canGoForward()) {
            this.webview.goForward();
        } else {
            Toast.makeText(getBaseContext(), "No existing link", Toast.LENGTH_SHORT).show();
        }
    }

    private void goRax() {
        this.webview.reload();
    }

    public void Refresh(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha));
        somethingToDo();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        System.gc();
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.f_b, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back /*2131755227*/:
                goBax();
                break;
            case R.id.reload /*2131755640*/:
                goRax();
                break;
            case R.id.forward /*2131755641*/:
                goFax();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void umustpay() {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setMessage(new SpannableString("Are you sure you want to exit about MFM ?"));
        alertDialogBuilder.setIcon(R.drawable.ic_warning);
        alertDialogBuilder.setTitle("Close browser?");
        alertDialogBuilder.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                OurSite.super.onBackPressed();

            }
        });
        alertDialogBuilder.setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }

    public boolean onSupportNavigateUp() {
        umustpay();
        return super.onSupportNavigateUp();
    }
}
