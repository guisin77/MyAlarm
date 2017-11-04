package com.yang.myalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class MyAlarmReceiver extends WakefulBroadcastReceiver {

    String RECORDED_FILE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS) + "/tmpsound.mp4" ;

    MediaCommon media = null;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Intent intent2;
        //perform your task
        Log.d("AAA" , "alarm ~~~~") ;

        intent = new Intent(context , AlarmPlayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP
                        |Intent.FLAG_ACTIVITY_NEW_TASK
                        |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

        intent2 = new Intent (context, MyAlarmService.class);
        intent2.putExtra("action" , "play");
        intent2.putExtra("soundFile" , RECORDED_FILE);
        context.startService(intent2);

    }
}
