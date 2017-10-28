package com.yang.myalarm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class AlarmListActivity extends AppCompatActivity {

    ImageButton btnAddAlarm, btnPlayThumb , btnStopThumb,  btnPlayThumb2 , btnStopThumb2;

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

        // Service 등록 ///////////////

        Intent intent = new Intent (AlarmListActivity.this, MyAlarmService.class);
        intent.putExtra("action" , "service");
        startService(intent);

        //////////////////////////////////////////////////////////////////////////

        btnAddAlarm  = (ImageButton)findViewById(R.id.btnAddAlarm);
        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlarmListActivity.this , AlarmDetailActivity.class);
                startActivity(intent);

            }
        });


//----------------------------------------------------------------------
        // Service call
        btnPlayThumb = (ImageButton)findViewById(R.id.btnPlayThumb);
        btnPlayThumb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent (AlarmListActivity.this, MyAlarmService.class);
                intent.putExtra("action" , "play");
                intent.putExtra("soundFile" , "/sdcard/tmpsound.mp4");
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
        // Common Service call


        btnPlayThumb2 = (ImageButton)findViewById(R.id.btnPlayThumb2);
        btnPlayThumb2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try {
                    MediaCommon media = new MediaCommon();
                    media.playSound("/sdcard/tmpsound.mp4");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        btnStopThumb2 = (ImageButton)findViewById(R.id.btnStopThumb2);
        btnStopThumb2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                MediaCommon media = new MediaCommon();
                media.killMediaPlay();
            }
        });

    }
}
