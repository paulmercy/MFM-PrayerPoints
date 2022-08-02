package com.paulmeric.mfmprayerpoints;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.util.Log;
import android.view.MenuItem;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdSize;
import com.adcolony.sdk.AdColonyAdView;
import com.adcolony.sdk.AdColonyAdViewListener;
import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryPerformance;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.tabs.TabLayout;
import com.kobakei.ratethisapp.RateThisApp;

import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegate;

import android.text.Html;
import android.view.Menu;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSION_SETTING = 101;
    private String admin_push_token = BuildConfig.APPLICATION_ID;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private SharedPreferences permissionStatus;
    String[] permissionsRequired = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.READ_CALENDAR", "android.permission.CALL_PHONE", "android.permission.READ_SMS", "android.permission.SEND_SMS"};
    ImageButton pudh;
    Boolean vic = Boolean.valueOf(false);
    private DrawerLayout mDrawerLayout;
    private boolean sentToSettings = false;
    private SharedPreferences sharedPreferences;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    private AutoCompleteTextView srch;
    private AutoCompleteTextView autoComplete;
    TextView txt2;
    private ArrayAdapter<String> adapter;
    private Switch myswitch;
    SharedPref sharedpref;
    private RelativeLayout adContainer;
    private AdColonyAdView adView;
    private static final String TAG = "MainActivity";
    private final String APP_ID = "app0fe92f52a900473e87";
    private final String ZONE_ID = "vzeb11c57010f841a9a9";



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if (sharedpref.loadNightModeState() == true) {
            setTheme(R.style.darktheme);
        } else setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        AdColony.configure(this, APP_ID, ZONE_ID);
        //Yahoo flurry analytics
        new FlurryAgent.Builder()
                .withDataSaleOptOut(false)
                .withCaptureUncaughtExceptions(true)
                .withIncludeBackgroundSessionsInMetrics(true)
                .withLogLevel(Log.VERBOSE)
                .withPerformanceMetrics(FlurryPerformance.ALL)
                .build(this, "TRFMSNNK2B9TKXZ9HD2C");

        setContentView(R.layout.activity_main);
        myswitch = findViewById(R.id.myswitch);
        if (sharedpref.loadNightModeState() == true) {
            myswitch.setChecked(true);
        }
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


        // Adding Toolbar to Main screen
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color='#ffffff'><small>MFM PrayerPoints</small></font>"));
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.setStatusBarColor(0);
        }
        RateThisApp.onCreate(this);
        RateThisApp.showRateDialogIfNeeded(this);

        // Setting ViewPager for each Tabs
        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        // Create Navigation drawer and inlfate layout
        NavigationView navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            VectorDrawableCompat indicator
                    = VectorDrawableCompat.create(getResources(), R.drawable.ic_menu, getTheme());
            assert indicator != null;
            indicator.setTint(ResourcesCompat.getColor(getResources(), R.color.white, getTheme()));
            supportActionBar.setHomeAsUpIndicator(indicator);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                } else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });
        // Set behavior of Navigation drawer
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    // This method will trigger on item Click of navigation menu
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        int id = menuItem.getItemId();
                        mFragmentManager = getSupportFragmentManager();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        if (id == R.id.nav_history) {
                            Intent po = new Intent(MainActivity.this, StandaloneExample.class);
                            po.putExtra("crisis", "file:///android_res/raw/history.htm");
                            startActivity(po);
                            overridePendingTransition(R.anim.abc_slide_in_top, R.anim.abc_slide_out_top);
                        } else if (id == R.id.web) {
                            Intent sit = new Intent(MainActivity.this, OurSite.class);
                            sit.putExtra(BuildConfig.APPLICATION_ID, "http://www.mountainoffire.org/");
                            startActivity(sit);
                            return true;

                        } else if (id == R.id.nav_share) {
                            alertRegF();
                        } else if (id == R.id.nav_send) {
                            startActivity(new Intent(MainActivity.this, Issue.class));
                        } else if (id == R.id.nav_exit) {
                            alert();
                        } else if (id == R.id.cont) {
                            Notification.Show(MainActivity.this);
                            return true;

                        } else if (id == R.id.nav_abtapp) {
                            AboutHpad.Show(MainActivity.this);
                        } else if (id == R.id.nav_spons) {
                            Spons.Show(MainActivity.this);
                        } else {
                            if (id == R.id.dev) {
                                Dialog dialogg = new Dialog(MainActivity.this);
                                dialogg.setContentView(R.layout.fragment_about);
                                dialogg.setTitle("About Us");
                                dialogg.findViewById(R.id.prayertopic).startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.move));
                                dialogg.show();
                            }
                        }


                        // Set item in checked state
                        menuItem.setChecked(true);

                        // TODO: handle navigation

                        // Closing drawer on item click
                        mDrawerLayout.closeDrawers();
                        return true;
                    }

                });
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING && ActivityCompat.checkSelfPermission(this, this.permissionsRequired[0]) == 0) {
            proceedAfterPermission();
        }
    }

    protected void onPostResume() {
        super.onPostResume();
        if (this.sentToSettings && ActivityCompat.checkSelfPermission(this, this.permissionsRequired[0]) == 0) {
            proceedAfterPermission();
        }
    }

    private void proceedAfterPermission() {

    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new PrayerMain(), "Prayer Points");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        int id = item.getItemId();
        Intent sit;
        if (id == R.id.twit) {
            sit = new Intent(this, OurSite.class);
            sit.putExtra(BuildConfig.APPLICATION_ID, "https://m.facebook.com/MFMWorldwide");
            startActivity(sit);
            return true;
        } else if (id == R.id.gmail) {
            alertReg();
            return true;

        } else if (id == R.id.dev) {
            Dialog dialogg = new Dialog(this);
            dialogg.setContentView(R.layout.fragment_about);
            dialogg.setTitle("About Us");
            dialogg.findViewById(R.id.prayertopic).startAnimation(AnimationUtils.loadAnimation(this, R.anim.move));
            dialogg.show();
            return true;

        } else if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);


        } else if (id == R.id.preq) {
            alertReg();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void alert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Dialog !");
        alertDialogBuilder.setMessage("Do you want to Close " + getString(R.string.app_name) + " ?");
        alertDialogBuilder.setIcon(R.drawable.ic_exity);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(DialogInterface dialog, int id) {
                Intent i = new Intent("android.intent.action.MAIN");
                i.addCategory("android.intent.category.HOME");
                i.setFlags(268435456);
                MainActivity.this.startActivity(i);
            }
        });
        alertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }

    private void alertReg() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Suggestion");
        alertDialogBuilder.setMessage("What would you like to suggest?");
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setNeutralButton("Continue ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, Gmail.class));
                MainActivity.this.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_right);
            }
        });
        alertDialogBuilder.create().show();
    }

    private void alertRegF() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Share");
        alertDialogBuilder.setMessage("Would you like to share this App ?");
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setNeutralButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent shareIntent = new Intent("android.intent.action.SEND");
                shareIntent.setType("text/plain");
                shareIntent.putExtra("android.intent.extra.TEXT", "Hi beloveth! You can now download the MFM PrayerPoints Android App from the link below:\n https://play.google.com/store/apps/details?id=com \nWe promise you'd be glad you did.Thanks alot.");
                MainActivity.this.startActivity(Intent.createChooser(shareIntent, "Share App"));
            }
        });
        alertDialogBuilder.create().show();
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @SuppressLint("WrongConstant")
    public void onBackPressed() {
        super.onBackPressed();

    }

    private void umustpay() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(new SpannableString("Alert!\n\nYou're about to notify us on an error you noticed in this App. Please, take note, Service Administrative Charges apply! (i.e SMS fee"));
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        alertDialogBuilder.create().show();
    }
    public void restartApp () {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
