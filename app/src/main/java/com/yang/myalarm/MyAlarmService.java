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

import static android.app.AlarmManager.INTERVAL_DAY;

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


            ////// 알람 목록   ////////////////////////////////////////

            String  alarmList = new MediaCommon().getAlarmList();
            String[] alarms  = alarmList.split("@");

            for(int i=0;i< alarms.length ; i++) {
             //   Toast.makeText(this, alarms[i], Toast.LENGTH_LONG).show();
                String tmp = alarms[i];
             //   "MMDD0630,1111100,001@"
//                 Log.d("AAA" , "tmp                ::" + tmp               );
//                 Log.d("AAA" , "tmp.substring(0,1) ::" + tmp.substring(0,2)); // MM
//                 Log.d("AAA" , "tmp.substring(2,4) ::" + tmp.substring(2,4)); // DD
//                 Log.d("AAA" , "tmp.substring(4,8) ::" + tmp.substring(4,8)); // 0630 time
//                 Log.d("AAA" , "tmp.substring(9,16) ::" + tmp.substring(9,16)); // 1111100 weeks
//                 Log.d("AAA" , "tmp.substring(17,20) ::" + tmp.substring(17,20)); // 001 sound

                int HOUR_OF_DAY = Integer.parseInt(  tmp.substring(4,6) ) ;
                int MINUTE      = Integer.parseInt(  tmp.substring(6,8) ) ;
                char weeks[]   = tmp.substring(9,16).toCharArray();

                ///////////////////////////////////////////////////////
                // Set the alarm to start at 8:30 a.m.
                Calendar calendar = Calendar.getInstance();
                long NOW = System.currentTimeMillis();
                calendar.setTimeInMillis(NOW);

                calendar.set(Calendar.HOUR_OF_DAY, HOUR_OF_DAY);
                calendar.set(Calendar.MINUTE, MINUTE);

                for(int j=0 ; j<= 6 ; j++ ) {
                    if ( weeks[j] == '1' ) {
                        calendar.setTimeInMillis(NOW);
                        calendar.add(Calendar.DATE, ( j+1 - calendar.DAY_OF_WEEK + 7) % 7  );
                        // weekly
                        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                7 * INTERVAL_DAY , alarmIntent);
                    }
                }
            }
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
