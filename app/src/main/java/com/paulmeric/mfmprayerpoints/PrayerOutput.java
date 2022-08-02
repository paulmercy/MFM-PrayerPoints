package com.paulmeric.mfmprayerpoints;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;

import androidx.appcompat.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdSize;
import com.adcolony.sdk.AdColonyAdView;
import com.adcolony.sdk.AdColonyAdViewListener;
import com.github.clans.fab.FloatingActionMenu;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Locale;

public class PrayerOutput extends AppCompatActivity  {
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    public static final String EXTRA_MESSAGE = "com.paulmeric.mfmprayerpoints.MESSAGE";
    final Context context = this;
    LinearLayout TTsmi;
    String autoC = BuildConfig.APPLICATION_ID;
    int j = DIALOG_DOWNLOAD_PROGRESS;
    String labels = "caption";
    FloatingActionMenu matDes;
    String messi = BuildConfig.APPLICATION_ID;
    String messo;
    double pitch = 0.0d;
    String senD;
    String senDa = BuildConfig.APPLICATION_ID;

    double speechRate = 0.0d;
    String text = BuildConfig.APPLICATION_ID;
    TextView txt2;
    GridView gv;
    private ArrayAdapter<String> adapter;
    private AutoCompleteTextView autoComplete;
    private ArrayList<Object> childItems = new ArrayList();
    private int currentSongIndex = DIALOG_DOWNLOAD_PROGRESS;
    private GestureDetector gestureDetector;
    private ProgressDialog mProgressDialog;
    private String output;
    private Resources resources;
    private AutoCompleteTextView srch;
    private Button startBtn;
    private TextToSpeech textToSpeech;
    SharedPref sharedpref;
    private RelativeLayout adContainer;
    private AdColonyAdView adView;
    private static final String TAG = "MainActivity";
    private final String APP_ID = "app0fe92f52a900473e87";
    private final String ZONE_ID = "vzeb11c57010f841a9a9";

    @SuppressLint("ResourceType")
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if (!sharedpref.loadNightModeState()) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.darktheme);
        }
        super.onCreate(savedInstanceState);
        String message = getIntent().getStringExtra("english");
        setContentView(R.layout.prayer_output);
        this.gestureDetector = new GestureDetector(new SwipeGestureDetector());
        adContainer = findViewById(R.id.ad_container);
        AdColonyAdViewListener listener = new AdColonyAdViewListener() {
            @Override
            public void onRequestFilled(AdColonyAdView adColonyAdView) {
                Log.d(TAG, "onRequestFilled");
                adContainer.addView(adColonyAdView);
                adView = adColonyAdView;
            }

            @Override
            public void onLeftApplication(AdColonyAdView ad) {
                super.onLeftApplication(ad);
                Log.d(TAG, "onLeftApplication");
            }
        };
        AdColony.requestAdView(ZONE_ID, listener, AdColonyAdSize.BANNER);

        Animation animationaa = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
        this.adapter = new ArrayAdapter(this, 17367043, getResources().getStringArray(R.array.searchtopics));
        this.autoComplete = findViewById(R.id.search);
        this.autoComplete.setAdapter(this.adapter);
        this.autoComplete.setThreshold(1);
        this.autoComplete.setCompletionHint("You could also use this view as index");
        this.autoComplete.setDropDownBackgroundResource(17301683);

        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.top);
        final HtmlTextView txt= findViewById(R.id.loadi);

        this.txt2 = findViewById(R.id.hymny);

        this.txt2.setText(message);
        this.txt2.startAnimation(animationaa);
        this.resources = getResources();

        /*Load Prayer Output*/
        try {
            this.output = LoadFile(message.replace("Text 00", "Text ").replace("Text 0", "Text ") + ".html", false);

            /*Set Movement Method as html*/
            txt.setHtml(this.output);
            this.txt2.setText(message);
        } catch (IOException e) {
            Toast.makeText(this, "Prayer: not found!", Toast.LENGTH_SHORT).show();
        }
        txt.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {
                CharSequence[] items = new CharSequence[]{"Prayer Read", "Share Prayer Points...", "Share with FB friends", "Report App error"};
                Builder builder = new Builder(PrayerOutput.this);
                builder.setTitle("More...");
                builder.setIcon(R.drawable.ic_assignmenti);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String userhint;
                        Intent shareIntent;
                        if (item == 0) {
                            Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                            /*PrayerOutput.this.TTsmi.setVisibility(PrayerOutput.DIALOG_DOWNLOAD_PROGRESS);
                            PrayerOutput.this.textToSpeech.setPitch((float) PrayerOutput.this.pitch);
                            PrayerOutput.this.textToSpeech.setSpeechRate((float) PrayerOutput.this.speechRate);
                            userhint = PrayerOutput.this.txt2.getText().toString() + "\n\n" + txt.getText().toString();
                            if (!PrayerOutput.this.textToSpeech.isSpeaking()) {
                                PrayerOutput.this.textToSpeech.speak(userhint, PrayerOutput.DIALOG_DOWNLOAD_PROGRESS, null);
                            }*/
                        } else if (item == 1) {
                            userhint = PrayerOutput.this.txt2.getText().toString() + "\n\n" + txt.getText().toString() + "\n\nSharedFrom : MFM PrayerPoint";
                            shareIntent = new Intent("android.intent.action.SEND");
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra("android.intent.extra.TEXT", userhint);
                            PrayerOutput.this.startActivity(Intent.createChooser(shareIntent, "Share this Prayer via..."));

                        } else if (item == 2) {
                            userhint = PrayerOutput.this.txt2.getText().toString() + "\n\n" + txt.getText().toString() + "\n\nSharedFrom : MFM Prayer";
                            shareIntent = new Intent("android.intent.action.SEND");
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra("android.intent.extra.TEXT", userhint);
                            for (ResolveInfo app : PrayerOutput.this.getPackageManager().queryIntentActivities(shareIntent, PrayerOutput.DIALOG_DOWNLOAD_PROGRESS)) {
                                if (app.activityInfo.name.contains("facebook")) {
                                    ActivityInfo activity = app.activityInfo;
                                    ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                                    shareIntent.addCategory("android.intent.category.LAUNCHER");
                                    shareIntent.setFlags(270532608);
                                    shareIntent.setComponent(name);
                                    PrayerOutput.this.startActivity(shareIntent);
                                    return;
                                }
                            }
                        } else {
                            final Dialog dialogg = new Dialog(PrayerOutput.this);
                            dialogg.setContentView(R.layout.dialogg);
                            dialogg.setTitle("Secured!");
                            ((TextView) dialogg.findViewById(R.id.gang)).setText("Notification of error in " + PrayerOutput.this.txt2.getText().toString());
                            dialogg.findViewById(R.id.verifier).setOnClickListener(new OnClickListener() {
                                public void onClick(View v) {
                                    final String checkers = ((EditText) dialogg.findViewById(R.id.admin)).getText().toString();
                                    if (checkers.contentEquals(BuildConfig.APPLICATION_ID)) {
                                        Toast.makeText(PrayerOutput.this.getApplicationContext(), "Please write a valid report! Field is blank!!!", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    Builder alertDialogBuilder = new Builder(PrayerOutput.this);
                                    alertDialogBuilder.setMessage(new SpannableString("Alert!\n\nYou're about to notify us on an error you noticed in this App. Please, take note, Service Administrative Charges apply! (i.e SMS fee)"));
                                    alertDialogBuilder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            try {
                                                SmsManager.getDefault().sendTextMessage("+2347033474776", null, "Notification of error in " + PrayerOutput.this.txt2.getText().toString() + "\n" + checkers, null, null);
                                                Toast.makeText(PrayerOutput.this.getApplicationContext(), "Request SMS Sent!\n\nCourtesy : MFM PrayerPoints SMS Manager", Toast.LENGTH_SHORT).show();
                                            } catch (Exception e) {
                                                Toast.makeText(PrayerOutput.this.getApplicationContext(), "SMS Sending failed, please try again later!", Toast.LENGTH_SHORT).show();
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    alertDialogBuilder.setNeutralButton("Abort", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                                    alertDialogBuilder.create().show();
                                }
                            });
                            dialogg.findViewById(R.id.canc).setOnClickListener(new OnClickListener() {
                                public void onClick(View v) {
                                    dialogg.cancel();
                                }
                            });
                            dialogg.show();
                        }
                    }
                });
                builder.create().show();
                return true;
            }
        });

    }


    public void onInit(int status) {
        if (status == 0) {
            int result = this.textToSpeech.setLanguage(Locale.US);
            if (result == -1 || result == -2) {
                Log.e("error", "This Language is not supported");
                return;
            }
            return;
        }
        Log.e("error", "Initialization Failed!");
    }

    public String LoadFile(String fileName, boolean loadFromRawFolder) throws IOException {
        InputStream iS;
        if (loadFromRawFolder) {
            iS = this.resources.openRawResource(this.resources.getIdentifier("fortyonepost.com.lfas:raw/" + fileName, null, null));
        } else {
            iS = this.resources.getAssets().open(fileName);
        }
        byte[] buffer = new byte[iS.available()];
        iS.read(buffer);
        ByteArrayOutputStream oS = new ByteArrayOutputStream();
        oS.write(buffer);
        oS.close();
        iS.close();
        return oS.toString();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hymn1, menu);
        return true;
    }

    @SuppressLint("WrongConstant")
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent d = getIntent();
        getIntent();
        final String message = d.getStringExtra("english");
        this.srch = findViewById(R.id.search);
        final String mymessage = this.srch.getText().toString();
        switch (item.getItemId()) {
            case R.id.down /*2131755072*/:
                Builder alertDialogBuilder1 = new Builder(this);
                alertDialogBuilder1.setTitle("Prayer Download !");
                alertDialogBuilder1.setIcon(R.drawable.ic_downl);
                final String Dstring1 = "Prayer_" + message.replaceAll("\\D+", BuildConfig.APPLICATION_ID).replaceAll("[^0-9]", BuildConfig.APPLICATION_ID).trim();
                final String Dstring2 = "Prayer_" + mymessage.replaceAll("\\D+", BuildConfig.APPLICATION_ID).replaceAll("[^0-9]", BuildConfig.APPLICATION_ID).trim();
                if (mymessage.contentEquals(BuildConfig.APPLICATION_ID)) {
                    alertDialogBuilder1.setMessage("Do you want to download the audio file(.mp3) of " + Dstring1 + " ?");
                    alertDialogBuilder1.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String url = "" + Dstring1 + ".mp3";
                            PrayerOutput.this.messo = message;
                            PrayerOutput.this.senD = "Prayer " + message.replaceAll("\\D+", BuildConfig.APPLICATION_ID).replaceAll("[^0-9]", BuildConfig.APPLICATION_ID).trim();
                            if (PrayerOutput.this.isOnline(PrayerOutput.this)) {
                                return;
                            }
                            Toast.makeText(PrayerOutput.this, "No network connection. Please switch on your Mobile Data or WIFI service and Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    alertDialogBuilder1.setMessage("Do you want to download the audio file(.mp3) of " + Dstring2 + " ?");
                    alertDialogBuilder1.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String url = "" + Dstring2 + ".mp3";
                            PrayerOutput.this.messo = mymessage;
                            PrayerOutput.this.senD = "Prayer " + mymessage.replaceAll("\\D+", BuildConfig.APPLICATION_ID).replaceAll("[^0-9]", BuildConfig.APPLICATION_ID).trim();
                            if (PrayerOutput.this.isOnline(PrayerOutput.this)) {
                                return;
                            }
                            Toast.makeText(PrayerOutput.this, "No network connection. Please switch on your Mobile Data or WIFI service and Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alertDialogBuilder1.setPositiveButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    alertDialogBuilder1.create().show();
                }
                this.senDa = this.senD;
                this.messi = this.messo;
                break;

            case R.id.sear /*2131755582*/:
                if (!this.autoComplete.isShown()) {
                    this.autoComplete.setVisibility(DIALOG_DOWNLOAD_PROGRESS);
                    this.autoComplete.setText(BuildConfig.APPLICATION_ID);
                    break;
                }
                this.autoComplete.setVisibility(8);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isOnline(Context mContext) {
        @SuppressLint("WrongConstant") NetworkInfo netInfo = ((ConnectivityManager) mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @SuppressLint("WrongConstant")
    public void onBackPressed() {
        super.onBackPressed();
        this.autoComplete.setVisibility(8);
    }

    @SuppressLint("WrongConstant")
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        this.autoComplete.setVisibility(8);
        this.srch = findViewById(R.id.search);
        String mymessage = this.srch.getText().toString();
        try {
            TextView txt = findViewById(R.id.loadi);
            txt.setMovementMethod(LinkMovementMethod.getInstance());
            this.txt2 = findViewById(R.id.hymny);
            Intent d = getIntent();
            if (this.textToSpeech.isSpeaking()) {
                this.textToSpeech.stop();
            }
            this.output = LoadFile(mymessage.replace("00", " ").replace("", " ") + ".txt", false);
            txt.setText(this.output);
            this.txt2.setText(mymessage);

        } catch (IOException e) {
            Toast.makeText(this, "File not found!", Toast.LENGTH_SHORT).show();
        }

    }

    private void umustpay() {
        Builder alertDialogBuilder = new Builder(this);
        alertDialogBuilder.setMessage(new SpannableString("Alert!\n\nYou're about to notify us on an error you noticed in this App. Please, take note, Service Administrative Charges apply! (i.e SMS fee"));
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }

    private void startDownload() {
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS /*0*/:
                String proname = BuildConfig.APPLICATION_ID;
                String message = getIntent().getStringExtra("english");
                this.srch = findViewById(R.id.search);
                if (this.srch.getText().toString().contentEquals(BuildConfig.APPLICATION_ID)) {
                    this.mProgressDialog = new ProgressDialog(this);
                    this.mProgressDialog.setMessage("Downloading prayer file... Please wait...");
                    this.mProgressDialog.setProgressStyle(1);
                    this.mProgressDialog.setCancelable(false);
                    this.mProgressDialog.show();
                    return this.mProgressDialog;
                }
                this.mProgressDialog = new ProgressDialog(this);
                this.mProgressDialog.setMessage("Downloading prayer file... Please wait...");
                this.mProgressDialog.setProgressStyle(1);
                this.mProgressDialog.setCancelable(false);
                this.mProgressDialog.show();
                return this.mProgressDialog;
            default:
                return null;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.gestureDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    private class SwipeGestureDetector extends SimpleOnGestureListener {
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        private SwipeGestureDetector() {
        }

    }
}
