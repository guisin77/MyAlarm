package com.yang.myalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AlarmListActivity extends AppCompatActivity {

    Button btnAddAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alarm_list);

        btnAddAlarm  = (Button)findViewById(R.id.btnAddAlarm);

        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlarmListActivity.this , AlarmDetailActivity.class);
                startActivity(intent);

            }
        });

    }
}
