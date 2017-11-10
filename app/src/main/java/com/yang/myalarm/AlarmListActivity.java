package com.yang.myalarm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class AlarmListActivity extends AppCompatActivity {

    ImageButton btnAddAlarm, btnPlayThumb , btnStopThumb,  btnPlayThumb2 , btnStopThumb2;

    String RECORDED_FILE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS) + "/tmpsound.mp4" ;



    MediaCommon media = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm_list);

        // 권한 체크
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                     Manifest.permission.RECORD_AUDIO
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ,Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }


        ////// Service 등록 (앱시작시,  부팅시 시작 ) ///////////////

        Intent intent = new Intent (AlarmListActivity.this, MyAlarmService.class);
        intent.putExtra("action" , "startService");
        startService(intent);

        //////////////////////////////////////////////////////////


        ListView listview ;
        AlarmListViewAdapter adapter;

        // Adapter 생성
        adapter = new AlarmListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);


       // adapter.addItem("111", "222", "333") ;

        ////// 알람 목록   ////////////////////////////////////////

        String  alarmList = new MediaCommon().getAlarmList();

        String[] alarms  = alarmList.split("@");


        for(int i=0;i< alarms.length ; i++) {
            Toast.makeText(this, alarms[i], Toast.LENGTH_LONG).show();
            String tmp = alarms[i];
            //   "MMDD0630,1111100,001@"
//            Log.d("AAA" , "tmp                ::" + tmp               );
//            Log.d("AAA" , "tmp.substring(0,1) ::" + tmp.substring(0,2)); // MM
//            Log.d("AAA" , "tmp.substring(2,4) ::" + tmp.substring(2,4)); // DD
//            Log.d("AAA" , "tmp.substring(4,8) ::" + tmp.substring(4,8)); // 0630 time
//            Log.d("AAA" , "tmp.substring(9,16) ::" + tmp.substring(9,16)); // 1111100 weeks
//            Log.d("AAA" , "tmp.substring(17,20) ::" + tmp.substring(17,20)); // 001 sound


            char weeks[]   = tmp.substring(9,16).toCharArray();

            String alarmWeeks = "";
            if(weeks[0]=='1')  alarmWeeks +="월 ";
            if(weeks[1]=='1')  alarmWeeks +="화 ";
            if(weeks[2]=='1')  alarmWeeks +="수 ";
            if(weeks[3]=='1')  alarmWeeks +="목 ";
            if(weeks[4]=='1')  alarmWeeks +="금 ";
            if(weeks[5]=='1')  alarmWeeks +="토 ";
            if(weeks[6]=='1')  alarmWeeks +="일 ";


            String soundFile = tmp.substring(17,20);

            adapter.addItem(tmp.substring(4,6) + ":" + tmp.substring(6,8), alarmWeeks, soundFile) ;

        }

     //////////////////////////////////////////////////////////////////////////

        btnAddAlarm  = (ImageButton)findViewById(R.id.btnAddAlarm);
        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlarmListActivity.this , AlarmDetailActivity.class);
                startActivity(intent);

            }
        });

/*
//----------------------------------------------------------------------
        // Service call
        btnPlayThumb = (ImageButton)findViewById(R.id.btnPlayThumb);
        btnPlayThumb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent (AlarmListActivity.this, MyAlarmService.class);
                intent.putExtra("action" , "play");
                intent.putExtra("soundFile" , RECORDED_FILE);
                startService(intent);
            }
        });


        btnStopThumb = (ImageButton)findViewById(R.id.btnStopThumb);
        btnStopThumb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent (AlarmListActivity.this, MyAlarmService.class);
                intent.putExtra("action" , "stop");
                startService(intent);
            }
        });


//----------------------------------------------------------------------
        // Common Method call


        btnPlayThumb2 = (ImageButton)findViewById(R.id.btnPlayThumb2);
        btnPlayThumb2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try {
                    media = new MediaCommon();
                    media.playSound(RECORDED_FILE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        btnStopThumb2 = (ImageButton)findViewById(R.id.btnStopThumb2);
        btnStopThumb2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if( media  != null)   media.killMediaPlay( );
            }
        });
*/
    }
}
