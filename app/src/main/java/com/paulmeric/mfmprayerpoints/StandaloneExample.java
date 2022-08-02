package com.paulmeric.mfmprayerpoints;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


public class StandaloneExample extends Activity {
    public static final String URL = "";
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    private static final String TAG = "WebscreenClass";
    HorizontalScrollView cool;
    int j = 0;
    String labels = "caption";
    ScrollView refresh;
    String[] s;
    int semesterValue = -1;
    TextView student_Id;
    String text = URL;
    String turl;
    WebView webview;
    private Button closeButton;
    private LinearLayout container;
    private Spinner day;
    private EditText findBox;
    private Spinner month;
    private Button nextButton;
    private String output;
    private ProgressDialog progressBar;
    private ProgressDialog progressDialog;
    private Resources resources;

    @SuppressLint("WrongConstant")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standalone_example);
        this.resources = getResources();
        ImageView tittle = findViewById(R.id.tittle);
        this.cool = findViewById(R.id.cool);
        this.webview = findViewById(R.id.webview);
        this.webview.getSettings().setBuiltInZoomControls(true);
        this.webview.getSettings().setDisplayZoomControls(false);
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.setBackgroundResource(R.drawable.bacc);
        this.cool.setVisibility(0);
        seracher();
        this.webview.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText(StandaloneExample.this.getApplicationContext(), "Sorry you're not Permitted to copy anything here.", Toast.LENGTH_SHORT).show();
                StandaloneExample.this.dothis2();
                return true;
            }
        });
        this.webview.setLongClickable(false);
        final AlertDialog alertDialog = new Builder(this).create();
        this.progressBar = ProgressDialog.show(this, null, "Loading... please wait");
        this.webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(StandaloneExample.TAG, "Processing webview url click...");
                view.loadUrl(StandaloneExample.this.turl);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(StandaloneExample.TAG, "Finished loading URL: " + StandaloneExample.this.turl);
                if (StandaloneExample.this.progressBar.isShowing()) {
                    StandaloneExample.this.progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(StandaloneExample.TAG, "Error: " + description);
                Toast.makeText(StandaloneExample.this, "Oh no! " + description, Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.setButton("OK", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            }
        });
        String test = getIntent().getStringExtra("crisis");
        String test2 = getIntent().getStringExtra("books");
        assert test != null;
        if (test.contentEquals("file:///android_res/raw/history.htm")) {
            if (savedInstanceState == null) {
                this.webview.loadUrl(test);
            } else {
                this.webview.restoreState(savedInstanceState);
            }

        } else if (savedInstanceState == null) {
            this.webview.loadUrl(test);
        } else {
            this.webview.restoreState(savedInstanceState);
        }
    }

    private void dothis2() {
        CharSequence[] items = new CharSequence[]{"Find...", "Make Notes", "Check Notes", "Day Mode", "Night Mode"};
        Builder builder = new Builder(this);
        builder.setTitle("Special Features");
        builder.setIcon(R.drawable.ic_arti);
        builder.setItems(items, new OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(DialogInterface dialog, int item) {
                if (item == 0) {
                    StandaloneExample.this.cool.setVisibility(0);
                    StandaloneExample.this.seracher();
                } else if (item == 2) {
                } else if (item == 3) {
                    StandaloneExample.this.webview.setBackgroundColor(0);
                    StandaloneExample.this.webview.loadUrl("javascript:document.body.style.setProperty(\"color\", \"black\");");
                } else {
                    StandaloneExample.this.webview.setBackgroundColor(Color.parseColor("#123456"));
                    StandaloneExample.this.webview.loadUrl("javascript:document.body.style.setProperty(\"color\", \"white\");");
                }
            }
        });
        builder.create().show();
    }

    @SuppressLint("WrongConstant")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            super.onActivityResult(requestCode, resultCode, data);

        } else {
            Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
        }
    }

    private void modes() {
        Builder alertDialogBuilder1 = new Builder(this);
        alertDialogBuilder1.setTitle("Special Features ");
        alertDialogBuilder1.setNegativeButton("Find...", new OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(DialogInterface dialog, int id) {
                StandaloneExample.this.cool.setVisibility(0);
                StandaloneExample.this.seracher();
            }
        });
        alertDialogBuilder1.setNeutralButton("Day Mode", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                StandaloneExample.this.webview.setBackgroundColor(0);
                StandaloneExample.this.webview.loadUrl("javascript:document.body.style.setProperty(\"color\", \"black\");");
            }
        });
        alertDialogBuilder1.setPositiveButton("Night Mode", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                StandaloneExample.this.webview.setBackgroundColor(Color.parseColor("#123456"));
                StandaloneExample.this.webview.loadUrl("javascript:document.body.style.setProperty(\"color\", \"white\");");
            }
        });
        alertDialogBuilder1.create().show();
    }

    private void seracher() {
        LayoutParams params = new LayoutParams(-2, -2);
        this.container = findViewById(R.id.layoutId);
        params.setMargins(3, 3, 3, 3);
        this.nextButton = new Button(this);
        this.nextButton.setText("Next");
        this.nextButton.setBackgroundResource(R.drawable.grey1);
        this.nextButton.setTextColor(getResources().getColor(R.color.black));
        this.nextButton.setLayoutParams(params);
        this.nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(StandaloneExample.this.getApplicationContext(), R.anim.alpha));
                StandaloneExample.this.webview.findNext(true);
            }
        });
        this.container.addView(this.nextButton);
        this.closeButton = new Button(this);
        this.closeButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        this.closeButton.setText("Close");
        this.closeButton.setBackgroundResource(R.drawable.grey1);
        this.closeButton.setTextColor(getResources().getColor(R.color.black));
        this.closeButton.setLayoutParams(params);
        this.closeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(StandaloneExample.this.getApplicationContext(), R.anim.alpha));
                StandaloneExample.this.cool.setVisibility(8);
                StandaloneExample.this.container.removeAllViews();
            }
        });
        this.container.addView(this.closeButton);
        this.findBox = new EditText(this);
        this.findBox.setMinEms(30);
        this.findBox.setBackgroundResource(R.drawable.grey1);
        this.findBox.setSingleLine(true);
        this.findBox.setLayoutParams(params);
        this.findBox.setHint("Search for anything in this book here...");
        this.findBox.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == 0 && keyCode == 66) {
                    StandaloneExample.this.webview.findAll(StandaloneExample.this.findBox.getText().toString());
                    try {
                        WebView.class.getMethod("setFindIsUp", Boolean.TYPE).invoke(StandaloneExample.this.webview, Boolean.valueOf(true));
                    } catch (Exception e) {
                    }
                }
                return false;
            }
        });
        this.container.addView(this.findBox);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.webview.canGoBack()) {
            return super.onKeyDown(keyCode, event);
        }
        this.webview.goBack();
        return true;
    }

    public void onBackPressed() {
        closer();
    }

    private void closer() {
        new Builder(this, R.style.ALERT_THEME).setMessage("Do you want to go back ?").setPositiveButton("YES", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                StandaloneExample.super.onBackPressed();
                StandaloneExample.this.finish();
            }
        }).setNegativeButton("NO", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        this.webview.saveState(outState);
    }

    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.webview.restoreState(savedInstanceState);
    }


    public class SemesterSelectedListener2 implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (pos == 0) {
                StandaloneExample.this.semesterValue = -1;
                return;
            }
            String semester = parent.getItemAtPosition(pos).toString();
            StandaloneExample.this.semesterValue = pos;
            StandaloneExample.this.findBox.setText(semester + " ");
        }

        public void onNothingSelected(AdapterView parent) {
        }
    }
}
