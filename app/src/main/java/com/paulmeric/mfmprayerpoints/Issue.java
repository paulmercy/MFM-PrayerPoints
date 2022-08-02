package com.paulmeric.mfmprayerpoints;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Objects;

public class Issue extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.paulmercy.mfmprayer.MESSAGE";
    public static final String EXTRA_MESSAGEE = "com.paulmercy.mfmprayer.MESSAGE";
    String address;
    String email;
    String mobile;
    String name;
    String sugg;
    RelativeLayout ww;
    EditText x1;
    EditText x2;
    EditText x3;
    EditText x4;
    EditText x5;
    private ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color='#ffffff'>Report an Issue</font>"));
        umustpay();
        this.x1 = findViewById(R.id.name);
        this.x2 = findViewById(R.id.email);
        this.x3 = findViewById(R.id.mobile);
        this.x4 = findViewById(R.id.address);
        this.x5 = findViewById(R.id.sugg);
        this.x5.setHint("What issue do you wish to report ? ");
        findViewById(R.id.sen).setOnClickListener(new OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Issue.this.getApplicationContext(), R.anim.alpha));
                Issue.this.name = Issue.this.x1.getText().toString();
                Issue.this.address = Issue.this.x4.getText().toString();
                Issue.this.email = Issue.this.x2.getText().toString();
                Issue.this.mobile = Issue.this.x3.getText().toString();
                Issue.this.sugg = Issue.this.x5.getText().toString();
                if (Issue.this.name.contentEquals(BuildConfig.APPLICATION_ID) || Issue.this.email.contentEquals(BuildConfig.APPLICATION_ID) || Issue.this.mobile.contentEquals(BuildConfig.APPLICATION_ID) || Issue.this.address.contentEquals(BuildConfig.APPLICATION_ID) || Issue.this.sugg.contentEquals(BuildConfig.APPLICATION_ID)) {
                    Toast.makeText(Issue.this, "Please Fill in the details", Toast.LENGTH_SHORT).show();
                } else if (Issue.this.isOnline(Issue.this)) {
                    Issue.this.progressDialog = ProgressDialog.show(Issue.this, "Message progress", "Sending Message...please wait");
                    new Thread(new Runnable() {
                        public void run() {
                            Intent emaila;
                            try {
                                Issue.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                    }
                                });
                                Issue.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Issue.this.progressDialog.dismiss();
                                    }
                                });
                                emaila = new Intent("android.intent.action.SEND");
                                emaila.putExtra("android.intent.extra.EMAIL", new String[]{"cpaul741@gmail.com"});
                                emaila.putExtra("android.intent.extra.SUBJECT", "My name is " + Issue.this.name);
                                emaila.putExtra("android.intent.extra.TEXT", "Phone no : " + Issue.this.mobile + "\n\nFrom : " + Issue.this.address + "\n\nE-mail : " + Issue.this.email + "\n\n\n\t\t\tISSUE TO REPORT :\n\n" + Issue.this.sugg);
                                emaila.setType("message/rfc822");
                                Issue.this.startActivity(Intent.createChooser(emaila, "Select Email Sender Client"));
                            } catch (Exception e) {
                                Log.e("log_tag", "Error in http connection!!" + e.toString());
                                Issue.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(Issue.this, "Please fill fields correctly", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Issue.this.runOnUiThread(/* anonymous class already generated */);
                                emaila = new Intent("android.intent.action.SEND");
                                emaila.putExtra("android.intent.extra.EMAIL", new String[]{"cpaul741@gmail.com"});
                                emaila.putExtra("android.intent.extra.SUBJECT", "My name is " + Issue.this.name);
                                emaila.putExtra("android.intent.extra.TEXT", "Phone no : " + Issue.this.mobile + "\n\nFrom : " + Issue.this.address + "\n\nE-mail : " + Issue.this.email + "\n\n\n\t\t\tISSUE TO REPORT :\n\n" + Issue.this.sugg);
                                emaila.setType("message/rfc822");
                                Issue.this.startActivity(Intent.createChooser(emaila, "Select Email Sender Client"));
                            } catch (Throwable th) {
                                Issue.this.runOnUiThread(/* anonymous class already generated */);
                                emaila = new Intent("android.intent.action.SEND");
                                emaila.putExtra("android.intent.extra.EMAIL", new String[]{"cpaul741@gmail.com"});
                                emaila.putExtra("android.intent.extra.SUBJECT", "My name is " + Issue.this.name);
                                emaila.putExtra("android.intent.extra.TEXT", "Phone no : " + Issue.this.mobile + "\n\nFrom : " + Issue.this.address + "\n\nE-mail : " + Issue.this.email + "\n\n\n\t\t\tISSUE TO REPORT :\n\n" + Issue.this.sugg);
                                emaila.setType("message/rfc822");
                                Issue.this.startActivity(Intent.createChooser(emaila, "Select Email Sender Client"));
                            }
                        }
                    }).start();
                    Issue.this.finish();
                } else {
                    Toast.makeText(Issue.this, "No network connection. Please switch on your Mobile Data or WIFI service ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void runOnUiThread() {
    }

    private boolean isOnline(Context mContext) {
        @SuppressLint("WrongConstant") NetworkInfo netInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void onDestroy() {
        super.onDestroy();
        System.gc();
        finish();
    }

    private void umustpay() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(new SpannableString("Do you wish to report an Issue?"));
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
