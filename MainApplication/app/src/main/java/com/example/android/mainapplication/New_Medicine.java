package com.example.android.mainapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class New_Medicine extends AppCompatActivity {


    public long mendmin,mendhour,mendday,mendmonth,mendyear,mstartmin,mstarthour,mstartyear,mstartmonth,mstartday;
    public static long startmillis,endmillis,milliTymGap;
    public static String medicineName;
    Button btnSelectDate,btnSelectTime;
    Button btnEndDate,btnEndTime;
    Button submit;
    EditText editTime,editMedicine;
    public String startTime, startDate,endDate,endTime;
    long milisec = 60*60*1000;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    static final int DATE_END_DIALOG_ID = 2;
    static final int TIME_END_DIALOG_ID=3;

    // variables to save user selected date and time
    public  int endsaveyear,endsavemonth,endsaveday,endsavehour,endsaveminute;
    // declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int endtYear, endMonth, endDay,endHour,endMinute;
    // variables to save user selected date and time
    public  int startsaveyear,startsavemonth,startsaveday,startsavehour,startsaveminute;
    // declare  the variables to Show/Set the date and time when Time and  Date Picker Dialog first appears
    private int startYear, startMonth, startDay,startHour,startMinute;
    // constructor

    public New_Medicine()
    {
        super();
        // Assign current Date and Time Values to Variables
        final Calendar c = Calendar.getInstance();
        startYear = c.get(Calendar.YEAR);
        startMonth = c.get(Calendar.MONTH);
        startDay = c.get(Calendar.DAY_OF_MONTH);
        startHour = c.get(Calendar.HOUR_OF_DAY);
        startMinute = c.get(Calendar.MINUTE);
        endtYear = c.get(Calendar.YEAR);
        endMonth = c.get(Calendar.MONTH);
        endDay = c.get(Calendar.DAY_OF_MONTH);
        endHour = c.get(Calendar.HOUR_OF_DAY);
        endMinute = c.get(Calendar.MINUTE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i("tanvy","hello");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__medicine);
        Log.i("tanvy","hello2");
        // get the references of buttons
        btnSelectDate=(Button)findViewById(R.id.buttonSelectDate);
        btnSelectTime=(Button)findViewById(R.id.buttonSelectTime);
        btnEndDate=(Button)findViewById(R.id.SelectEndDate);
        btnEndTime=(Button)findViewById(R.id.SelectEndTime);
        editTime = (EditText)findViewById(R.id.time_dis);
        editMedicine = (EditText)findViewById(R.id.medicine);
        submit = (Button)findViewById(R.id.submit_alarm);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(New_Medicine.this,
                        "Alarm created", Toast.LENGTH_SHORT).show();
                Alarm_Thread alarm = new Alarm_Thread(New_Medicine.this);
                alarm.setalarm();
            }
        });


        // Set ClickListener on btnSelectDate
        editTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String mytimestr = editTime.getText().toString();
                Log.i("tanvy","time gap"+ mytimestr);
                Integer mytymGap = Integer.parseInt(mytimestr);
                milliTymGap = mytymGap*60*60*1000;
                Log.i("tanvy","time gap"+ milliTymGap);
            }
        });
        editMedicine.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String myMedicine = editMedicine.getText().toString();
                Log.i("tanvy","time =="+ myMedicine);
            }
        });
        btnSelectDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the DatePickerDialog
                showDialog(DATE_DIALOG_ID);
            }
        });

        // Set ClickListener on btnSelectTime
        btnSelectTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the TimePickerDialog
                showDialog(TIME_DIALOG_ID);
            }
        });
        btnEndDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the DatePickerDialog
                showDialog(DATE_END_DIALOG_ID);
            }
        });
        btnEndTime.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the DatePickerDialog
                showDialog(TIME_END_DIALOG_ID);
            }
        });
    }


    // Register  DatePickerDialog listener
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                // the callback received when the user "sets" the Date in the DatePickerDialog
                public void onDateSet(DatePicker view, int yearSelected,
                                      int monthOfYear, int dayOfMonth) {
                    startsaveyear = yearSelected;
                    startsavemonth = monthOfYear;
                    startsaveday = dayOfMonth;
                   // mstartday = startDay*milisec*24;
                    //mstartmonth = startsavemonth*milisec*30*24;
                    //mstartyear = startsaveyear*milisec*12*30*24;
                    //Log.i("tanvy"," 1 d"+ String.valueOf(mstartday));
                    //Log.i("tanvy"," 1  m"+ String.valueOf(mstartmonth));
                    //Log.i("tanvy"," 1  y"+ String.valueOf(mstartyear));

                    // Set the Selected Date in Select date Button
                    btnSelectDate.setText("Date selected : "+startsaveday+"-"+startsavemonth+"-"+startsaveyear);
                    startDate = startsaveday+"-"+startsavemonth+"-"+startsaveyear ;
                }
            };
    // Register  TimePickerDialog listener
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                    startsavehour = hourOfDay;
                    startsaveminute = min;
                    /*mstartmin = startsaveminute*60*1000;
                    mstarthour = startsavehour*60*60*1000;
                    Log.i("tanvy"," 1 hr"+ String.valueOf(mstartmin));
                    Log.i("tanvy"," 1  min"+ String.valueOf(mstarthour));*/
                    // Set the Selected Date in Select date Button
                    btnSelectTime.setText("Time selected :"+startsavehour+"-"+startsaveminute);
                     startTime = +startsavehour+":"+startsaveminute;
                    String toParse = startDate + " " + startTime;
                    SimpleDateFormat startFormatter = new SimpleDateFormat("d-M-yyyy hh:mm");
                    Date date = null; // You will need try/catch around this
                    try {
                        date = startFormatter.parse(toParse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                     startmillis = date.getTime();
                    Log.i("tanvy"," start"+ String.valueOf(startmillis));
                }
            };
    // Register  DatePickerDialog listener
    private DatePickerDialog.OnDateSetListener mEndDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                // the callback received when the user "sets" the Date in the DatePickerDialog
                public void onDateSet(DatePicker view, int yearSelected,
                                      int monthOfYear, int dayOfMonth) {
                    endsaveyear = yearSelected;
                    endsavemonth = monthOfYear;
                    endsaveday = dayOfMonth;
                    // Set the Selected Date in Select date Button
                    btnEndDate.setText("Date selected : "+endsaveday+"-"+endsavemonth+"-"+endsaveyear);
                    endDate = startsaveday+"-"+startsavemonth+"-"+startsaveyear ;
                }
            };
    // Register  TimePickerDialog listener
    private TimePickerDialog.OnTimeSetListener mEndTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                    endsavehour = hourOfDay;
                    endsaveminute = min;
                    // Set the Selected Date in Select date Button
                    btnEndTime.setText("Time selected :"+endsavehour+"-"+endsaveminute);
                    endTime = +endsavehour+":"+endsaveminute;
                    String toParse = endDate + " " + endTime;
                    SimpleDateFormat endFormatter = new SimpleDateFormat("d-M-yyyy hh:mm");
                    Date enddate = null; // You will need try/catch around this
                    try {
                        enddate = endFormatter.parse(toParse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    endmillis = enddate.getTime();
                    Log.i("tanvy"," end" +
                            ""+ String.valueOf(endmillis));
                }
            };

    // Method automatically gets Called when you call showDialog()  method
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // create a new DatePickerDialog with values you want to show
                return new DatePickerDialog(this,
                        mDateSetListener,
                        startYear, startMonth, startDay);
            case DATE_END_DIALOG_ID:
                // create a new DatePickerDialog with values you want to show
                return new DatePickerDialog(this,
                        mEndDateSetListener,
                        endtYear, endMonth, endDay);
            // create a new TimePickerDialog with values you want to show
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, startHour, startMinute, false);
            // create a new TimePickerDialog with values you want to show
            case TIME_END_DIALOG_ID:
                return new TimePickerDialog(this,
                        mEndTimeSetListener, endHour, endMinute, false);

        }
        return null;
    }

}
