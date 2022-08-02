package com.paulmeric.mfmprayerpoints;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.util.Linkify;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Notification {
    static String VersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @SuppressLint("WrongConstant")
    public static void Show(Activity callingActivity) {
        View about;
        TextView tvAbout = null;
        SpannableString aboutText = new SpannableString("\nMFM PrayerPoints\n\n" + callingActivity.getString(R.string.mfmc));
        try {
            about = callingActivity.getLayoutInflater().inflate(R.layout.activity_notification, (ViewGroup) callingActivity.findViewById(R.id.aboutView));
            tvAbout = about.findViewById(R.id.aboutText);
        } catch (InflateException e) {
            View tvAbout2 = new TextView(callingActivity);
            about = tvAbout2;
        }
        assert tvAbout != null;
        tvAbout.setText(aboutText);
        Linkify.addLinks(tvAbout, 15);
        new Builder(callingActivity).setCancelable(true).setTitle("MFM WorldWide Contact").setIcon(R.mipmap.ic_launcher).setView(about).show();
    }

    public void Sender(View v) {
    }
}
