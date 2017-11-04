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
          "MMDD0630,1111100,001@"
       ,  "MMDD0900,0000011,001@"
       ,  "MMDD2100,1111111,001@" };

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

       // setAlarmList(null);

        try {
            //읽을 파일
            File readFile = new File(ALARM_FILE_PATH);
            br = new BufferedReader(new FileReader(readFile));

            String line = "";
            int i=0;
            while ((line = br.readLine()) != null) {
                alarmList = line;
                //읽을 한 줄을 ,로 구분하여 스트링 배열로 리턴
  //              String[] texts = line.split(",");
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
}
