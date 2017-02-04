package com.example.android.mainapplication;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Tika_page extends AppCompatActivity {

    public static  final int u_id = 1000;
    public static final String TNAME = "TITLE";
    public static final String TDOSE = "DOSE";
    public static final int day_in_sec = 24 * 60 * 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tika_page);

    }

    public void setAlarm(View view){
//        //Debug
//        Intent ii = new Intent(getApplicationContext(), alarm_activity.class);
//        startActivity(ii);
//        return;


        DatePicker p = (DatePicker) findViewById(R.id.datePicker);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, p.getYear());
        cal.set(Calendar.MONTH, p.getMonth());
        cal.set(Calendar.DATE, p.getDayOfMonth());
        cal.set(Calendar.HOUR, 10);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);

        AlarmManager mn = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        setAl(cal, 7 * day_in_sec, mn, "BCG", "First");
        setAl(cal, 7 * day_in_sec, mn, "Oral Polio", "First");
        setAl(cal, 6 * 7 * day_in_sec, mn, "Oral Polio", "Second");
        setAl(cal, 10 * 7 * day_in_sec, mn, "Oral Polio", "Third");
        setAl(cal, 14 * 7 * day_in_sec, mn, "Oral Polio", "Fourth");
        setAl(cal, 18 * 30 * day_in_sec, mn, "Oral Polio", "Fifth");
        setAl(cal, 5 * 365 * day_in_sec, mn, "Oral Polio", "Sixth");

        setAl(cal, 7 * day_in_sec, mn, "Hepatites B", "First");
        setAl(cal, 6 * 7 * day_in_sec, mn, "Hepatities B", "Second");
        setAl(cal, 6 * 30 * day_in_sec, mn, "Hepatities B", "Third");

        setAl(cal, 6 * 7 * day_in_sec, mn, "DPT", "First");
        setAl(cal, 10 * 7 * day_in_sec, mn, "DPT", "Second");
        setAl(cal, 14 * 7 * day_in_sec, mn, "DPT", "Third");

        setAl(cal, 14 * 7 * day_in_sec, mn, "Hib", "First");
        setAl(cal, 20 * 7 * day_in_sec, mn, "Hib", "Second");
        setAl(cal, 6 * 30 * day_in_sec, mn, "Hib", "Third");

        setAl(cal, 9 * 30 * day_in_sec, mn, "Measles", "First");

        setAl(cal, 365 * day_in_sec, mn, "Varilix", "First");

        setAl(cal, 18 * 30 * day_in_sec, mn, "MMR", "First");
        setAl(cal, 12 * 365 * day_in_sec, mn, "MMR", "Second");

        setAl(cal, 2 * 365 * day_in_sec, mn, "Typhoid", "First");

        setAl(cal, 10 * 365 * day_in_sec, mn, "TT", "First");
        setAl(cal, 15 * 365 * day_in_sec, mn, "TT", "Second");
    }

    private void setAl(Calendar cal, int sec, AlarmManager mn, String n, String d){

        cal.add(Calendar.SECOND, sec);
        Log.i("tanvy", String.valueOf(cal));
        Intent i = new Intent(Tika_page.this, alarm_activity.class);
        i.putExtra(TNAME, n);
        i.putExtra(TDOSE, d);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), u_id, i, PendingIntent.FLAG_IMMUTABLE, null);
        mn.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
        cal.add(Calendar.SECOND, -1 * sec);
    }
}
