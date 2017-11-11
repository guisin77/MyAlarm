package com.yang.myalarm;

import android.media.MediaPlayer;

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

    public void setAlarmList(String  newAlarm) {
        BufferedWriter bw = null;
        String data[] = {
          "MMDD1900,1111100,001@"
       ,  "MMDD1800,0000011,001@"
       ,  "MMDD1700,1111111,001@" };

        try {
            //쓸 파일
            File writeFile = new File(ALARM_FILE_PATH);
            if (!writeFile.exists()) writeFile.createNewFile();

            bw = new BufferedWriter(new FileWriter(writeFile, true));
/*
            for (int i = 0; i < data.length ;i++){
                bw.write(data[i]);
               // bw.newLine();
            }
*/
           // bw.write(getAlarmList());

            if(newAlarm!=null) {
                      bw.append(newAlarm);
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

       // setAlarmList(null);

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

        if(alarmList==null) alarmList ="";

        return alarmList;
    }


 //-----------------------------------------------------------

    public void setSoundList(String  newSound) {
        BufferedWriter bw = null;

        try {
            //쓸 파일
            File writeFile = new File(SOUND_FILE_PATH);
            if (!writeFile.exists()) writeFile.createNewFile();
            bw = new BufferedWriter(new FileWriter(writeFile, true));

            //bw.write(getSoundList());

            if(newSound!=null) {
                bw.append(newSound);
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
        String  alarmList =null;


        try {
            //읽을 파일
            File readFile = new File(SOUND_FILE_PATH);
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
        if(alarmList==null) alarmList ="";
        return alarmList;
    }

}
