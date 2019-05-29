package com.example.demo.service.impl;

import com.example.demo.dao.PersonnelCountDao;
import com.example.demo.model.PersonnelCount;
import com.example.demo.service.PCService;
import com.example.demo.util.Config;
import com.example.demo.util.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PCServiceImpl implements PCService {

    @Autowired
    private PersonnelCountDao personnelCountDao;

    @Override
    public Map<String, Integer> getCurrentPC() {
        Map<String, Integer> map = new HashMap<>();
        map.put("linac", personnelCountDao.selectNewestPCByPosition("LINAC_COUNT").get(0).getCount());
        map.put("ringCenter", personnelCountDao.selectNewestPCByPosition("RINGCENTER_COUNT").get(0).getCount());
        map.put("ringHall", personnelCountDao.selectNewestPCByPosition("RINGHALL_COUNT").get(0).getCount());
        return map;
    }

    @Override
    public List<PersonnelCount> getPCByPosition(String position, long start_time, long end_time) {
        if (!position.equalsIgnoreCase("linac") &&
                !position.equalsIgnoreCase("ringCenter") &&
                !position.equalsIgnoreCase("ringHall")) {
            return null;
        }
        String table_name = position + "_count";
        return personnelCountDao.selectPCByPosition(table_name, start_time, end_time);
    }

    @Override
    public String writeToFile(String[] positionList, long start_time, long end_time) throws IOException {
        if (positionList == null || positionList.length == 0) {
           return null;
        }
        String nameStr = "";
        for (int i = 0; i < positionList.length; i++) {
            nameStr = nameStr + "_" + positionList[i];
        }
        nameStr = nameStr + "_"+ start_time;
        nameStr = nameStr + "_" + end_time;
        nameStr = nameStr + "_" + Math.random();
        String filename = Config.DEFAULT_PATH + "/records/"+ nameStr + ".txt";
        File file = new File(filename);
        for (int i = 0; i < positionList.length; i++) {
            String table_name = positionList[i] + "_count";
            List<PersonnelCount> pcList = personnelCountDao.selectPCByPosition(table_name, start_time, end_time);
            ExportUtil.exportHeader(file, positionList[i], start_time, end_time);
            ExportUtil.exportPv(file, start_time, end_time, pcList);
        }
        return nameStr + ".txt";
    }
}
