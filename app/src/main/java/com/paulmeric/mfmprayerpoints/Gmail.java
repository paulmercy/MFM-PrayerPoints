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
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Gmail extends AppCompatActivity {
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

        umustpay();
        this.x1 = findViewById(R.id.name);
        this.x2 = findViewById(R.id.email);
        this.x3 = findViewById(R.id.mobile);
        this.x4 = findViewById(R.id.address);
        this.x5 = findViewById(R.id.sugg);
        findViewById(R.id.sen).setOnClickListener(new OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(Gmail.this.getApplicationContext(), R.anim.alpha));
                Gmail.this.name = Gmail.this.x1.getText().toString();
                Gmail.this.address = Gmail.this.x4.getText().toString();
                Gmail.this.email = Gmail.this.x2.getText().toString();
                Gmail.this.mobile = Gmail.this.x3.getText().toString();
                Gmail.this.sugg = Gmail.this.x5.getText().toString();
                if (Gmail.this.name.contentEquals(BuildConfig.APPLICATION_ID) || Gmail.this.email.contentEquals(BuildConfig.APPLICATION_ID) || Gmail.this.mobile.contentEquals(BuildConfig.APPLICATION_ID) || Gmail.this.address.contentEquals(BuildConfig.APPLICATION_ID) || Gmail.this.sugg.contentEquals(BuildConfig.APPLICATION_ID)) {
                    Toast.makeText(Gmail.this, "Please Fill in the details", Toast.LENGTH_SHORT).show();
                } else if (Gmail.this.isOnline(Gmail.this)) {
                    Gmail.this.progressDialog = ProgressDialog.show(Gmail.this, "Message progress", "Sending Message...please wait");
                    new Thread(new Runnable() {
                        public void run() {
                            Intent emaila;
                            try {
                                Gmail.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                    }
                                });
                                Gmail.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Gmail.this.progressDialog.dismiss();
                                    }
                                });
                                emaila = new Intent("android.intent.action.SEND");
                                emaila.putExtra("android.intent.extra.EMAIL", new String[]{"chijiokeogbonnaya13@gmail.com"});
                                emaila.putExtra("android.intent.extra.SUBJECT", "My name is " + Gmail.this.name);
                                emaila.putExtra("android.intent.extra.TEXT", "Phone no : " + Gmail.this.mobile + "\n\nFrom : " + Gmail.this.address + "\n\nE-mail : " + Gmail.this.email + "\n\n\n\t\t\tMESSAGE BODY :\n\n" + Gmail.this.sugg);
                                emaila.setType("message/rfc822");
                                Gmail.this.startActivity(Intent.createChooser(emaila, "Select Email Sender Client"));
                            } catch (Exception e) {
                                Log.e("log_tag", "Error in http connection!!" + e.toString());
                                Gmail.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(Gmail.this, "Please fill fields correctly", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Gmail.this.runOnUiThread(/* anonymous class already generated */);
                                emaila = new Intent("android.intent.action.SEND");
                                emaila.putExtra("android.intent.extra.EMAIL", new String[]{"cpaul741@gmail.com"});
                                emaila.putExtra("android.intent.extra.SUBJECT", "My name is " + Gmail.this.name);
                                emaila.putExtra("android.intent.extra.TEXT", "Phone no : " + Gmail.this.mobile + "\n\nFrom : " + Gmail.this.address + "\n\nE-mail : " + Gmail.this.email + "\n\n\n\t\t\tMESSAGE BODY :\n\n" + Gmail.this.sugg);
                                emaila.setType("message/rfc822");
                                Gmail.this.startActivity(Intent.createChooser(emaila, "Select Email Sender Client"));
                            } catch (Throwable th) {
                                Gmail.this.runOnUiThread(/* anonymous class already generated */);
                                emaila = new Intent("android.intent.action.SEND");
                                emaila.putExtra("android.intent.extra.EMAIL", new String[]{"cpaul741@gmail.com"});
                                emaila.putExtra("android.intent.extra.SUBJECT", "My name is " + Gmail.this.name);
                                emaila.putExtra("android.intent.extra.TEXT", "Phone no : " + Gmail.this.mobile + "\n\nFrom : " + Gmail.this.address + "\n\nE-mail : " + Gmail.this.email + "\n\n\n\t\t\tMESSAGE BODY :\n\n" + Gmail.this.sugg);
                                emaila.setType("message/rfc822");
                                Gmail.this.startActivity(Intent.createChooser(emaila, "Select Email Sender Client"));
                            }
                        }
                    }).start();
                    Gmail.this.finish();
                } else {
                    Toast.makeText(Gmail.this, "No network connection. Please switch on your Mobile Data or WIFI service ", Toast.LENGTH_SHORT).show();
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
        alertDialogBuilder.setMessage(new SpannableString("Please Message your suggestions. Thanks!"));
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
