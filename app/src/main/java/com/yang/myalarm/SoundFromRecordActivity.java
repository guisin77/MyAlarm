package com.yang.myalarm;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SoundFromRecordActivity extends AppCompatActivity {

    Button btnRecord, btnStop, btnPlay  ;
    private MediaPlayer player ;
    private MediaRecorder recorder;
    private int playbackPosition = 0;
    //String RECORDED_FILE = "/sdcard/tmpsound.mp4" ;
    String RECORDED_FILE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS) + "/tmpsound.mp4" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_from_record);


        btnRecord  = (Button)findViewById(R.id.btnRecord);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnPlay = (Button)findViewById(R.id.btnPlay);


        btnRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(recorder!=null) {
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                }

                Log.d("AAA", RECORDED_FILE );
                recorder = new MediaRecorder();

                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(RECORDED_FILE);

                try {
                    recorder.prepare();
                    recorder.start();  // 녹음 시작
                    Log.d("AAA" , "Record start");
                    Toast.makeText(SoundFromRecordActivity.this, "Record", Toast.LENGTH_LONG).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        btnStop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(recorder ==null)  return;

                recorder.stop();
                recorder.release();
                recorder = null;
                Toast.makeText(SoundFromRecordActivity.this, "Stop",Toast.LENGTH_LONG).show();
                Log.d("AAA" , "Record stop");

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // play
                if (playbackPosition ==0 ) {
                    try {
                        playSound(RECORDED_FILE);
                        Log.d("AAA" , "Play start");
                        btnPlay.setText("Play Stop");
                        Toast.makeText(SoundFromRecordActivity.this,"Play start ",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                // play stop
                if(playbackPosition > 0 && player!=null) {
                    playbackPosition = player.getCurrentPosition();
                    player.pause();
                    Toast.makeText(SoundFromRecordActivity.this,"Play Pause",Toast.LENGTH_LONG).show();
                    btnPlay.setText("Play");
                    Log.d("AAA" , "Play Pause");
                }

                /*
                // play restart
                if (playbackPosition > 0 && player!=null && !player.isPlaying()) {
                    player.seekTo(playbackPosition);
                }
                */
            }
        });



    }

    private void playSound(String soundFile ) throws Exception {
        killMediaPlay();

        player = new MediaPlayer();
        player.setDataSource(soundFile);
        player.prepare();
        player.start();
    }

    private void killMediaPlay() {
        if(player !=null ) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}