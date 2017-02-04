package com.example.android.mainapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.example.android.mainapplication.httprequest.HttpListener;
import com.example.android.mainapplication.httprequest.HttpRequest;
import com.example.android.mainapplication.httprequest.urls;

import org.json.JSONException;
import org.json.JSONObject;

public class Register_Activity extends AppCompatActivity implements HttpListener {
    private static final int REQUEST_EMAIL_VALIDATION = 1212;
    private EditText emailField,passwordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findAllViews();
        initClickListeners();
    }

    private void initClickListeners() {
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailField.getText().toString().trim().equals("") && passwordField.getText().toString().trim().equals(""))
                {
                    final Dialog dialog = new Dialog(Register_Activity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dailogeone);
                    dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return;
                }

                final String email = emailField.getText().toString();
                final Dialog dialog = new Dialog(Register_Activity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dailogeone);
                dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if(!Register_Activity.isValidEmailAddress(email))
                {
                    final Dialog dialog2 = new Dialog(Register_Activity.this);
                    dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog2.setContentView(R.layout.dailogeerror_login);
                    dialog2.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });
                    dialog2.show();

                    return;
                }
                else
                {
                    final Dialog confDialog = new Dialog(Register_Activity.this);
                    confDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    confDialog.setContentView(R.layout.dailogeregistration);
                    TextView emailField = (TextView) confDialog.findViewById(R.id.email);
                    emailField.setText(email+" is this correct ?");
                    confDialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HttpRequest request = new HttpRequest(urls.domainAddress+"",REQUEST_EMAIL_VALIDATION,Register_Activity.this);
                            request.setRawJson("email",email);
                            request.setRawJson("tag", "register");
                            request.execute("post");
                            confDialog.dismiss();
                        }
                    });
                    confDialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confDialog.dismiss();
                        }
                    });
                    confDialog.show();


                }
            }
        });
    }
    private void findAllViews()
    {
        emailField = (EditText) findViewById(R.id.email);
        passwordField = (EditText) findViewById(R.id.password);
    }
    @Override
    public void respond(String jsonRespond, int respondId, HttpRequest parent) {
        try {
            boolean isValidEmail = new JSONObject(jsonRespond).getJSONObject("jsonResponse").getBoolean("status");
            if(isValidEmail)
            {
                Intent intent = new Intent(Register_Activity.this,MainActivity.class);
                intent.putExtra("email",emailField.getText().toString());
                intent.putExtra("password",passwordField.getText().toString());
                startActivity(intent);
                finish();
            }
            else
            {
                final Dialog dialog = new Dialog(Register_Activity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dailogeerror_registration);
                dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void errorRespond(VolleyError e, int respondId, HttpRequest parent) {
        if(e instanceof NoConnectionError)
        {
            Toast.makeText(Register_Activity.this,"No internet connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Context getContext() {
        return Register_Activity.this;
    }
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
