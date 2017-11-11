package com.yang.myalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlarmPlayActivity extends AppCompatActivity {
    Button btnStop;
    Button btnAlarmList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm_play);

        btnStop = (Button)findViewById(R.id.btnStop);
        btnAlarmList = (Button)findViewById(R.id.btnAlarmList);

        btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(AlarmPlayActivity.this, "Stop!!!!", Toast.LENGTH_LONG).show();;

                Intent intent = new Intent (AlarmPlayActivity.this, MyAlarmService.class);
                intent.putExtra("action" , "stop");
                startService(intent);

            }
        });

        btnAlarmList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlarmPlayActivity.this , AlarmListActivity.class);
                startActivity(intent);

            }
        });

       // finish();

    }
}
