package com.example.demo.service.impl;

import com.example.demo.dao.DivisionDao;
import com.example.demo.model.Division;
import com.example.demo.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionDao divisionDao;

    @Override
    public List<Division> getAllDivision() {
        return divisionDao.selectAllDivision();
    }
}
