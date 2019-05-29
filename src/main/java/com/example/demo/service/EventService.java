package com.example.demo.service;

import com.example.demo.model.Event;
import com.example.demo.model.Record;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public interface EventService {
    List<Record> getEventByTime(long startTime,long endTime);
    List<Record> getEventByCardIdAndTime( int card_id, long startTime, long endTime);
    List<Record> getEventByDivisionIdAndTime(int division_id, long startTime, long endTime);
    List<Record> getEventByDoorAndTime(String doorName, long startTime, long endTime);
}
