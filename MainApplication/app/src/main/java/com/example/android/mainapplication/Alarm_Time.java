package com.example.android.mainapplication;

import io.realm.RealmObject;

/**
 * Created by NIHAL on 10/29/2016.
 */
public class Alarm_Time extends RealmObject{
    private  String medicineName;
    private long timegap;
    private int id;

    public int getId() {
        return id;
    }

    public long getTimegap() {
        return timegap;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setTimegap(long timegap) {
        this.timegap = timegap;
    }
}
