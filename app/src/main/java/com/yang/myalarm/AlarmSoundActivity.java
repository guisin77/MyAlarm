package com.yang.myalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AlarmSoundActivity extends AppCompatActivity {

    Button btnAddSound ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_sound);

      //  TextView tv =  ((TextView)findViewById(R.id.alarmListView)) ;

        String  soundList = new MediaCommon().getSoundList();

        //tv.append("tmpfile.mp4");

        if (soundList!=null) {
            String[] sounds;
            sounds =  soundList.split("@");
/*
            for (int i = 0; i < sounds.length; i++) {
                tv.append(sounds[i]);
            }
*/
            //---------------------------------------
            try {

                ArrayList<String> strings = new ArrayList<String>();
                for (int i = 0; i < sounds.length; i++) {
                    strings.add(sounds[i]);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        AlarmSoundActivity.this, android.R.layout.simple_list_item_1, strings);
                ListView listView = (ListView)findViewById(R.id.listview_sound);
                listView.setAdapter (adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
            //---------------------------------------------------

        }





        btnAddSound  = (Button)findViewById(R.id.btnAddSound);

        btnAddSound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AlarmSoundActivity.this , SoundMenuActivity.class);
                startActivity(intent);

            }
        });

       // finish();
    }
}
