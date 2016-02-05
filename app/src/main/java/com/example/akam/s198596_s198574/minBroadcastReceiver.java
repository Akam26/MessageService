package com.example.akam.s198596_s198574;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Akam on 22.10.15.
 */
public class minBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       // if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
        Log.d("!!!!!!!!!!!1", "---------------");
            Intent i = new Intent(context, MsgPeriodiskService.class);
            context.startService(i);
        //}

    }

}
