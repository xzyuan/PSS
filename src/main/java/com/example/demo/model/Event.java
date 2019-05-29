package com.example.demo.model;

public class Event {
    private int id ;
    private int card_id;
    private String reader_name;
    //时间以存储unix毫秒时间戳
    private long swipe_time;
    private int swipe_result;

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public long getSwipe_time() {
        return swipe_time;
    }

    public void setSwipe_time(long swipe_time) {
        this.swipe_time = swipe_time;
    }

    public int getSwipe_result() {
        return swipe_result;
    }

    public void setSwipe_result(int swipe_result) {
        this.swipe_result = swipe_result;
    }
}
