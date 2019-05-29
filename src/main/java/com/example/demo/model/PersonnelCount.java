package com.example.demo.model;

import java.sql.Timestamp;

public class PersonnelCount {
    private long time;
    private int count;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTimeString(){
        String str = new Timestamp(time).toString();
        str = str.substring(0,19);
        return str;
    }
}
