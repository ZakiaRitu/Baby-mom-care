package com.example.android.mainapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Splash_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);
    }


    public void signin(View view) {
        Intent i = new Intent(Splash_Activity.this, Login_Activity.class);
        startActivity(i);
    }

    public void create(View view) {
        Intent i = new Intent(Splash_Activity.this, Register_Activity.class);
        startActivity(i);
    }

    public void menu(View view) {
        Intent i = new Intent(Splash_Activity.this, MainActivity.class);
        startActivity(i);
    }
}
