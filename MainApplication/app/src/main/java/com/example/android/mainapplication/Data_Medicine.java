package com.example.android.mainapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.android.gms.common.api.GoogleApiClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Data_Medicine extends AppCompatActivity {
    EditText edittext;
    EditText edittime;
    EditText firstDate;
    EditText firstTime;
    EditText endDate;
    EditText endTime;
    EditText timegap;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__medicine);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firstDate = (EditText) findViewById(R.id.start_date_input);
        firstDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(edittext);
            }
        });
        firstTime = (EditText) findViewById(R.id.start_time_input);
        firstDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(edittime);
            }
        });
        endDate = (EditText) findViewById(R.id.end_date_input);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(edittext);
            }
        });
        endTime = (EditText) findViewById(R.id.end_time_input);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(edittime);
            }
        });
        timegap = (EditText) findViewById(R.id.time_gap);
        timegap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(edittime);
            }
        });
    }

    public void setTime(final EditText edittime) {
        final Calendar myCalendarTime = Calendar.getInstance();
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendarTime.set(Calendar.HOUR_OF_DAY,hourOfDay);
                myCalendarTime.set(Calendar.MINUTE,minute);
                String myFormat = "Hr/mn";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
                edittime.setText(sdf.format(myCalendarTime.getTime()));
            }
        };
        edittime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(Data_Medicine.this,time, myCalendarTime.get(Calendar.HOUR_OF_DAY),myCalendarTime.get(Calendar.MINUTE),true).show();
            }
        });
    }

    public void setDate(final EditText edittext) {
        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Data_Medicine.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }
}
