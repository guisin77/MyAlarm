package com.yang.myalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static com.yang.myalarm.R.id.btnAddSound;

public class AlarmDetailActivity extends AppCompatActivity {

     Button btnAlarmSound ;
     Button btnAlarmAdd;
    TimePicker timePicker1;
    private TextView time;
    private Calendar calendar;
    private String format = "";

    boolean onMon ;
    boolean onTue ;
    boolean onWed ;
    boolean onThr ;
    boolean onFri ;
    boolean onSat ;
    boolean onSun ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);

        btnAlarmSound  = (Button)findViewById(R.id.btnAlarmSound);
        btnAlarmAdd = (Button)findViewById(R.id.btnAlarmAdd);

        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);



        btnAlarmSound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlarmDetailActivity.this , AlarmSoundActivity.class);
                startActivity(intent);

            }
        });


        btnAlarmAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(AlarmDetailActivity.this, "Save click" , Toast.LENGTH_LONG).show();

                //timePicker1.setHour(5);
                //timePicker1.setMinute(5);
                String newAlarm =  "MMDD0000,0000000,001@" ;

                newAlarm = "MMDD";
                int tmpHour = timePicker1.getHour();
                int tmpMinute = timePicker1.getMinute();

                String hour = "" ;
                String minute = "" ;

                if (tmpHour < 10 ) hour = "0" + tmpHour ;
                else  hour = ""+ tmpHour;

                if (tmpMinute < 10 ) minute = "0" + tmpMinute ;
                else  minute = ""+  tmpMinute;

                onMon  = ((CheckBox)findViewById(R.id.onMon)).isChecked() ;
                onTue  = ((CheckBox)findViewById(R.id.onTue)).isChecked() ;
                onWed  = ((CheckBox)findViewById(R.id.onWed)).isChecked() ;
                onThr  = ((CheckBox)findViewById(R.id.onThr)).isChecked() ;
                onFri  = ((CheckBox)findViewById(R.id.onFri)).isChecked() ;
                onSat  = ((CheckBox)findViewById(R.id.onSat)).isChecked() ;
                onSun  = ((CheckBox)findViewById(R.id.onSun)).isChecked() ;

                newAlarm =    "MMDD" + hour  + minute + "," ; // "0000000" + ",001@" ;
                if (onMon)   newAlarm +=  "1" ;  else newAlarm +=  "0" ;
                if (onTue)   newAlarm +=  "1" ;  else newAlarm +=  "0" ;
                if (onWed)   newAlarm +=  "1" ;  else newAlarm +=  "0" ;
                if (onThr)   newAlarm +=  "1" ;  else newAlarm +=  "0" ;
                if (onFri)   newAlarm +=  "1" ;  else newAlarm +=  "0" ;
                if (onSat)   newAlarm +=  "1" ;  else newAlarm +=  "0" ;
                if (onSun)   newAlarm +=  "1" ;  else newAlarm +=  "0" ;

                newAlarm += "," + "001" + "@";

                Log.d("AAA", "newAlarm :: " + newAlarm);
                // save
                (new MediaCommon()).setAlarmList(newAlarm);

                ////// Service 등록 (앱시작시,  부팅시 , 새로운 알람 등록시 시작 ) ///////////////

                Intent intent =new Intent(  AlarmDetailActivity.this, MyAlarmService.class);
                intent.putExtra("action" , "startService");
                startService(intent);


                 // 화면이동
                intent = new Intent(AlarmDetailActivity.this , AlarmListActivity.class);
                startActivity(intent);

            }
        });



       // finish();
    }
}
