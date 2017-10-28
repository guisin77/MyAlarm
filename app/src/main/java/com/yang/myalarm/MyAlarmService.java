package com.yang.myalarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MyAlarmService extends Service {


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

        String action = intent.getStringExtra("action");
        Log.d("AAA", "action ::: " + action );

        if ("service".equals(action)) {
            // 일상 알림

            for ( int i = 1 ; i< 3; i++ ) {
                try {

                    Log.d("AAA", "Service!!!!!!!!!!!!!!!!!!    " + i );
                    playSound("/sdcard/tmpsound.mp4");
                    Thread.sleep(1000 * 30);
                } catch (Exception e) {
                    e.printStackTrace();
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
