package com.yang.myalarm;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


/**
 * Created by 50137458 on 2017-10-28.
 */

public class MediaCommon {


    private MediaPlayer player ;

    private static final String ALARM_FILE_PATH =  "/sdcard/alarmList.txt";
    private static final String SOUND_FILE_PATH =  "/sdcard/soundList.txt";

    public void playSound(String soundFile ) throws Exception {
        killMediaPlay();

        player = new MediaPlayer();
        player.setDataSource(soundFile);
        player.prepare();
        player.start();
    }

    public void killMediaPlay( ) {
        if(player !=null ) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setAlarmList(String[] data2) {
        BufferedWriter bw = null;
        String data[] = {
          "MMDD1942,1111100,001@"
       ,  "MMDD1946,0000011,001@"
       ,  "MMDD1947,1111111,001@" };

        try {
            //쓸 파일
            File writeFile = new File(ALARM_FILE_PATH);
            if (!writeFile.exists()) writeFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(writeFile));

            for (int i = 0; i < data.length ;i++){
                bw.write(data[i]);
               // bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (Exception ignore) {
            }
        }
    }

    public String  getAlarmList() {
        BufferedReader br = null;
        String  alarmList =null;

        setAlarmList(null);

        try {
            //읽을 파일
            File readFile = new File(ALARM_FILE_PATH);
            br = new BufferedReader(new FileReader(readFile));

            String line = "";
            int i=0;
            while ((line = br.readLine()) != null) {
                alarmList = line;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (Exception ignore) {
            }
        }
        return alarmList;
    }



    public void setSoundList(String data2) {
        BufferedWriter bw = null;

        Log.d("AAA",">>> " + data2);
        try {
            //쓸 파일
            File writeFile = new File(SOUND_FILE_PATH);
            if (!writeFile.exists()) {
                writeFile.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(writeFile));

           if(data2!=null) {
               String asis = getSoundList()  ;
               if ( asis!=null ) bw.write( asis + data2);
               else  bw.write (data2);
               Log.d("AAA", "write +++ " + data2);
           }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (Exception ignore) {
            }
        }
    }




    public String  getSoundList() {
        BufferedReader br = null;
        String  soundList =null;

        //setSoundList(null);

        try {
            //읽을 파일
            File readFile = new File(SOUND_FILE_PATH);
            br = new BufferedReader(new FileReader(readFile));

            String line = "";
            int i=0;
            while ((line = br.readLine()) != null) {
                soundList = line;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (Exception ignore) {
            }
        }
        return soundList;
    }

}
