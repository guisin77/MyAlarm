package com.yang.myalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;

public class MyAlarmService extends Service {


    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private MediaPlayer player ;

    public MyAlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("AAA" , "Service onCreate called!!");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        if (intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void processCommand(Intent intent) {
        Intent intent2 ;
        String action = intent.getStringExtra("action");
        Log.d("AAA", "action ::: " + action );

        if ("stopService".equals(action)) {
            alarmMgr =  (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent2 = new Intent(this, MyAlarmReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(this, 0, intent2, 0);

            if (alarmMgr!= null) {
                alarmMgr.cancel(alarmIntent);
            }
        } else if ("startService".equals(action)) {

            alarmMgr =  (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            intent2 = new Intent(this, MyAlarmReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(this, 0, intent2, 0);

            //삭제
            if (alarmMgr!= null) {
                alarmMgr.cancel(alarmIntent);
            }

            // Set the alarm to start at 8:30 a.m.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 13);
            calendar.set(Calendar.MINUTE, 42);

//        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, alarmIntent);

            //1분
            alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    1 * 60 * 1000 , alarmIntent);


        }
        else if("play".equals(action)) {
            String soundFile = intent.getStringExtra("soundFile");

            Log.d("AAA", "soundFile ::: " + soundFile );
            try {
                playSound(soundFile);
                Log.d("AAA" , "Play start");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if ("stop".equals(action)) {
            killMediaPlay();
        }

    }

    private void playSound(String soundFile ) throws Exception {
        killMediaPlay();

        player = new MediaPlayer();
        player.setDataSource(soundFile);
        player.prepare();
        player.start();
    }

    private void killMediaPlay() {
        if(player !=null ) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
