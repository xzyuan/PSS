package com.example.demo.service.impl;

import com.example.demo.dao.EventDao;
import com.example.demo.model.Event;
import com.example.demo.model.Record;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventDao eventDao;
    @Override
    public List<Record> getEventByTime(long startTime, long endTime) {
        System.out.println(eventDao.selectEventBySwipeTime(startTime,endTime).size());
        return eventDao.selectEventBySwipeTime(startTime,endTime);
    }

    @Override
    public List<Record> getEventByCardIdAndTime(int card_id, long startTime, long endTime) {
        return eventDao.selectEventByCardIdAndSwipeTime(card_id, startTime, endTime);
    }

    @Override
    public List<Record> getEventByDivisionIdAndTime(int division_id, long startTime, long endTime) {
        return eventDao.selectEventByDivisionIdAndSwipeTime(division_id, startTime, endTime);
    }

    @Override
    public List<Record> getEventByDoorAndTime(String doorName, long startTime, long endTime) {
        return eventDao.selectEventByReaderNameAndSwipeTime(doorName, startTime, endTime);
    }
}
