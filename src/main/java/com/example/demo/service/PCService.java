package com.example.demo.service;


import com.example.demo.model.PersonnelCount;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PCService {
    Map<String,Integer> getCurrentPC();
    List<PersonnelCount> getPCByPosition(String position, long start_time, long end_time);
    String writeToFile(String[] positionList, long start_time, long end_time) throws IOException;
}
