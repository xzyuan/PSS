package com.example.demo.service;


import com.example.demo.model.StaffInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StaffService {
    int insertStaff(int card_id, String name, int division_id);

    List<StaffInfo> getAllStaffInfo();

    int deleteStaffByCardId(int card_id);
}
