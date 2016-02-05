package com.example.akam.s198596_s198574;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Akam on 22.10.15.
 */
public class MsgPeriodiskService extends Service {
    final int EVERY_DAY = 1000 * 60 * 60 * 24;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Calendar cal = Calendar.getInstance();
        Intent in = new Intent(this, MsgService.class);
        //PendingIntent pintent = PendingIntent.getService(this,0,in,0);

        PendingIntent pintent = PendingIntent.getService(this, 0, new Intent(this, MsgService.class), 0);

        //cal.add(Calendar.MINUTE, 1);
        cal.set(Calendar.MINUTE, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("minutt", 1));
        cal.set(Calendar.HOUR_OF_DAY, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("time", 1));

        Log.d("textHere", "perServ");
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), EVERY_DAY, pintent);

        //.onStartCommand(intent, flags, startId)
        return Service.START_NOT_STICKY;
    }

    /*@Override
    public void onDestroy() {
        super.onDestroy();
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        PendingIntent pSmsIntent = PendingIntent.getService(this, 0, new Intent(this, MsgService.class), 0);
        alarmManager.cancel(pSmsIntent);
        editor.apply();
        stopSelf();
    }*/
}
