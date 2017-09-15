package com.yang.myalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlarmDetailActivity extends AppCompatActivity {

     Button btnAlarmSound ;
     Button btnAlarmAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);

        btnAlarmSound  = (Button)findViewById(R.id.btnAlarmSound);
        btnAlarmAdd = (Button)findViewById(R.id.btnAlarmAdd);

        btnAlarmSound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlarmDetailActivity.this , AlarmSoundActivity.class);
                startActivity(intent);

            }
        });


        btnAlarmAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(AlarmDetailActivity.this, "Save click" , Toast.LENGTH_LONG).show();

            }
        });
    }
}
