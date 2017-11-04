package com.yang.myalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBootReceiver extends BroadcastReceiver {

    // boot call
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            // Set the alarm here.
            intent = new Intent (context, MyAlarmService.class);
            intent.putExtra("action" , "startService");
            context.startService(intent);
        }
    }
}
