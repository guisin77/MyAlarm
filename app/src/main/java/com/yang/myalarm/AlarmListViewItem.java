package com.yang.myalarm;

/**
 * Created by yang on 2017. 11. 6..
 */

public class AlarmListViewItem {
    private String time;
    private String repeatWeeks;
    private String alarmSound;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRepeatWeeks() {
        return repeatWeeks;
    }

    public void setRepeatWeeks(String repeatWeeks) {
        this.repeatWeeks = repeatWeeks;
    }

    public String getAlarmSound() {
        return alarmSound;
    }

    public void setAlarmSound(String alarmSound) {
        this.alarmSound = alarmSound;
    }
}
