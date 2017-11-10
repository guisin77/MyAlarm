package com.yang.myalarm;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.yang.myalarm.R.id.btnPlayThumb2;

/**
 * Created by yang on 2017. 11. 6..
 */

public class AlarmListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<AlarmListViewItem> alarmListViewItemList = new ArrayList<AlarmListViewItem>() ;

    // ListViewAdapter의 생성자
    public AlarmListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return alarmListViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alarm_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView timeTextView = (TextView) convertView.findViewById(R.id.txtTime) ;
        TextView weekTextView = (TextView) convertView.findViewById(R.id.txtWeek) ;
        TextView soundTextView = (TextView) convertView.findViewById(R.id.txtSound) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        AlarmListViewItem alarmListViewItem = alarmListViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영

        timeTextView.setText(alarmListViewItem.getTime());
        weekTextView.setText(alarmListViewItem.getRepeatWeeks());
        soundTextView.setText(alarmListViewItem.getAlarmSound());



        // button1 클릭 시 TextView(textView1)의 내용 변경.
//    Button button1 = (Button) convertView.findViewById(R.id.button1);
//        button1.setOnClickListener(new Button.OnClickListener() {
//        public void onClick(View v) {
//            textTextView.setText(Integer.toString(pos + 1) + "번 아이템 선택.");
//        }
//    });

        final MediaCommon media2 = new MediaCommon();;
        final String RECORDED_FILE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS) + "/tmpsound.mp4" ;

        ImageButton btnPlayThumb = (ImageButton)convertView.findViewById(btnPlayThumb2);
        btnPlayThumb.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                /*
                Intent intent = new Intent (this, MyAlarmService.class);
                intent.putExtra("action" , "play");
                intent.putExtra("soundFile" , RECORDED_FILE);
                startService(intent);
                */
                Toast.makeText(context, pos+1 +"clicked" , Toast.LENGTH_LONG).show();

                        try {
                            media2.playSound(RECORDED_FILE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


            }
        });

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return alarmListViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String time, String weeks, String sound) {
        AlarmListViewItem item = new AlarmListViewItem();

        item.setTime(time);
        item.setRepeatWeeks(weeks);
        item.setAlarmSound(sound);

        alarmListViewItemList.add(item);
    }
}
