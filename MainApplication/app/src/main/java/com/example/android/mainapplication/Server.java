package com.example.android.mainapplication;



import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nihal pd on 4/3/2016.
 */

/**
 * Created by tanveer on 8/17/15.*/
public class Server {
    private SQLiteDatabase db;

    public class FeedReaderDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "CardSaver.db";

        public FeedReaderDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table Student(name varchar(30),location varchar(30), comment varchar(60))");
            initDatabase("Sefali Begum", "Dhaka", "What i should do reduce my fever???", db);
            initDatabase("Dr.Abdur Rahman","Health specilist","You should go to take medicine",db);
            initDatabase("Rahima Khatun", "Rajshahi","What should do to recover my health...???",db);
            initDatabase("Dr. Habibur Rahman", "Brain specialist","Goto the nearest Hospital",db);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL("drop table Student if exists");
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

        public void initDatabase(String name,String location,String comment,SQLiteDatabase db)
        {
            db.execSQL("insert into Student values('" + name + "','" + location + "','"+comment+"')");
        }
    }
    public void open(Context app)
    {
        FeedReaderDbHelper helper =new FeedReaderDbHelper(app);
        db=helper.getWritableDatabase();

    }
    public void close()
    {
        db.close();
    }

    public void insertStudent(String name,String location,String comment)
    {
        db.execSQL("insert into Student values('"+name+"','"+location+"','"+comment+"')");
    }
    public Cursor grab()
    {
        Cursor cursor = db.rawQuery("select * from Student",null);
        return cursor;

    }

}

