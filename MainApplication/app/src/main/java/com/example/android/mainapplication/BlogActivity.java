package com.example.android.mainapplication;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;


public class BlogActivity extends AppCompatActivity {
    //private String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    //private String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    private Server database;
    private String m_Text;
    private String name;
    private String location;
    private String comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_blog);
        setSupportActionBar(toolbar);
        database = new Server();
        database.open(this);
        Cursor students = database.grab();
        ListView listView = (ListView) findViewById(R.id.activity_list);
        CustomAdapter adapter = new CustomAdapter(this,students);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BlogActivity.this);
                builder.setTitle("Submit your question");


                LinearLayout layout = new LinearLayout(getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText inputName = new EditText(getApplicationContext());
                inputName.setHint("Name");
                layout.addView(inputName);

                final EditText inputLocation = new EditText(getApplicationContext());
                inputLocation.setHint("Location");
                layout.addView(inputLocation);

                final EditText inputComment = new EditText(getApplicationContext());
                inputComment.setHint("comment");
                layout.addView(inputComment);

                builder.setView(layout);

                // Set up the input

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = inputName.getText().toString();
                        location = inputLocation.getText().toString();
                        comment = inputComment.getText().toString();
                        database.insertStudent(name, location, comment);



                        Cursor students = database.grab();
                        ListView listView = (ListView) findViewById(R.id.activity_list);
                        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),students);
                        listView.setAdapter(adapter);
                        Snackbar.make(view, "Our Doctor will anwer you as soon as possible", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();




            }
        });
        //ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mobileArray);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
