package com.example.akam.s198596_s198574;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Akam on 22.10.15.
 */
public class MsgService extends Service {
    @Nullable
    DBAdapter db;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Toast.makeText(MsgService.this, "i service", Toast.LENGTH_SHORT).show();

        db = new DBAdapter(this);
        db.open();
        Calendar today = Calendar.getInstance();
        int dag = today.get(Calendar.DAY_OF_MONTH);
        int man = today.get(Calendar.MONTH);
        Log.d("textHere"," " + dag);
        Cursor lst = db.bursdagIDag(dag, man);
        SmsManager smsManager = SmsManager.getDefault();
        String mld = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("melding","Gratulerer");
        if (lst.moveToFirst()) {
            do {
                smsManager.sendTextMessage(lst.getString(lst.getColumnIndex(db.TELEFON)), null, mld, null, null);
            }while (lst.moveToNext());
        }
        lst.close();

        //smsManager.sendTextMessage(lst.getString(lst.getColumnIndex(db.TELEFON)), null,"gratulerer!!!", null, null);

        return super.onStartCommand(intent, flags, startId);
    }
}
