package com.example.android.mainapplication;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MedicineActovity extends AppCompatActivity {

    EditText et;
    int n;
    Button bt, okbt;
    ViewGroup outer;
    EditText ns[], ds[];

    public static final int INTERVEL_IN_SEC = 6 * 60 * 60;
    public static final String tag = "TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_actovity);

        et = (EditText)findViewById(R.id.numberOfMed);
        bt = (Button) findViewById(R.id.button200);
        okbt = (Button) findViewById(R.id.medicineOKbutton);
        okbt.setVisibility(View.INVISIBLE);
        outer = (ViewGroup)findViewById(R.id.lineraOuter);

        //for the number of medicine//
        ns = new EditText[100];
        //for the number of dosses//
        ds = new EditText[100];

        okbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                for(int i = 0; i < n; ++i){
                    //store the number of dosses into string//
                    String s = ds[i].getText().toString();
                    //then covert it to int//
                    int dos = Integer.parseInt(s);
                    //then go to the alarm activity throught intent//
                    Intent intent = new Intent(MedicineActovity.this, MedicineAlarm.class);
                    //ns[i] will send the name of medine to the medicine alarm activity//
                    intent.putExtra(tag, ns[i].getText().toString());
                    PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 1000, intent, PendingIntent.FLAG_IMMUTABLE, null);
                    //the loop will continue until the doss are completed//
                    for(int j = 1; j <= dos; ++j){
                        cal.add(Calendar.SECOND, j * INTERVEL_IN_SEC);
                        //then the alram manager will  awake cal.add(calender time) will equal to the setted time//
                        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);
                        //then reducing 1 dose it will again add alarm//
                        cal.add(Calendar.SECOND, -1 * j * INTERVEL_IN_SEC);
                    }
                }
                Toast.makeText(getApplicationContext(), "Alarm is on", Toast.LENGTH_LONG).show();
            }
        });
    }
    //if will depened on the number of medicine we pressed//
    public void Clicked(View view) {
        n = 0;

        String s = et.getText().toString();
        try{
            n = Integer.parseInt(s);
        } catch (Exception e){

        }
        //if we pressed 0 then the ok button wiil not visible//
        if(n == 0){
            okbt.setVisibility(View.INVISIBLE);
            return;
        }
        //if pressed > 0 then the linear layout will visible with //
        //the name of medicine and the number o dosses//
        LinearLayout lin = (LinearLayout)findViewById(R.id.linerInner);
        if(lin.getChildCount() > 0)
            lin.removeAllViews();
        for(int i = 0; i < n; ++i) {
            ns[i] = getEditView("Name Of Medicine");
            ds[i] = getEditView("Doses");
            lin.addView(ns[i]);
            lin.addView(ds[i]);
        }
//the 2nd ok button will visible in here//
        okbt.setVisibility(View.VISIBLE);

        outer.invalidate();
    }


    private EditText getEditView(String hint){
        EditText et = new EditText(this);
        et.setHint(hint);
        return et;
    }
}
