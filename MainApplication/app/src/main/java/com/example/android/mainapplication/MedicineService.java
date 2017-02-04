package com.example.android.mainapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by NIHAL on 10/29/2016.
 */
public class MedicineService extends Service {
    Thread medicineThred;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        medicineThred = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                        long time = System.currentTimeMillis();
                        Realm realm = Realm.getInstance(MedicineService.this);
                        Alarm_Time alarm = realm.where(Alarm_Time.class).lessThan("timegap", time).findFirst();
                        if(alarm!=null){
                            notifyMed();

                        }

                        Log.i("tanvy","entered ="+ alarm);

                        RealmResults<Alarm_Time> alarms = realm.where(Alarm_Time.class).findAll();
                        Log.i("my seted alarms",""+alarms.size());
                        for(int i= 0;i<=alarms.size();i++){
                            Log.i("my seted alarms",""+alarms);
                        }



                    } catch (Exception e) {
                        //TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        medicineThred.start();
    }

    private void notifyMed(){
        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.backd) // notification icon
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.baby))
                .setContentTitle("Mediicine alarm") // title for notification
                .setContentText("take medicine") // message for notification
                .setAutoCancel(true)
                .setAutoCancel(true);

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mNotificationManager.notify(10001, mBuilder.build());
    }

}
