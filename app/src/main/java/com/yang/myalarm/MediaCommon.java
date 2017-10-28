package com.yang.myalarm;

import android.media.MediaPlayer;

/**
 * Created by 50137458 on 2017-10-28.
 */

public class MediaCommon {


    private MediaPlayer player ;

    public void playSound(String soundFile ) throws Exception {
        killMediaPlay();

        player = new MediaPlayer();
        player.setDataSource(soundFile);
        player.prepare();
        player.start();
    }

    public void killMediaPlay() {
        if(player !=null ) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
