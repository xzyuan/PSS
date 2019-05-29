package com.example.demo.service.impl;

import com.example.demo.dao.StaffDao;
import com.example.demo.model.StaffInfo;
import com.example.demo.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffDao staffDao;

    @Override
    public int insertStaff(int card_id, String name, int division_id){
        return staffDao.insertStaff(card_id, name, division_id);
    }

    @Override
    public List<StaffInfo> getAllStaffInfo() {
        return staffDao.findAllStaffInfo();
    }

    @Override
    public int deleteStaffByCardId(int card_id) {
        return staffDao.deleteByCardId(card_id);
    }
}
