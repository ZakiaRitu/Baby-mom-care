package com.example.android.mainapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AgeWeight extends AppCompatActivity {

    EditText age, wi;
    Button bt;

    double arr[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_weight);


        initArray();

        age = (EditText) findViewById(R.id.agewi);
        wi = (EditText) findViewById(R.id.agewikg);
        bt = (Button) findViewById(R.id.agewibtn);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //store the input of age and weight in string//
                String a = age.getText().toString();
                String b = wi.getText().toString();
                //convert them into int//
                int m = Integer.parseInt(a);
                double d = Double.parseDouble(b);

                //after giving weight and age that coment box for result//
                AlertDialog.Builder builder = new AlertDialog.Builder(AgeWeight.this);
                builder.setTitle("Result for your Baby");
                //tv variable will show the result//
                TextView tv = new TextView(AgeWeight.this);
                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();;
                    }
                });
                //arr[m] = which is age && d =  weight//
                //by calculating it will show the matched result//
                if(Math.abs(arr[m] - d) <= 0.5){
                    tv.append("Standard Weight");
                } else if(arr[m] < d){
                    tv.append("Over Weight");
                } else {
                    tv.append("Under Weight");
                }

                builder.setView(tv);
                builder.show();
            }
        });
    }

    private void initArray(){
        arr = new double[100];
        arr[0] = 3.0;
        arr[1] = 4.4;
        arr[2] = 5.6;
        arr[3] = 6.4;
        arr[4] = 7.0;
        arr[5] = 7.5;
        arr[6] = 7.9;
        arr[7] = 8.3;
    }
}
