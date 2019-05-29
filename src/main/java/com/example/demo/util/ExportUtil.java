package com.example.demo.util;

import com.example.demo.model.PersonnelCount;

import java.io.*;
import java.sql.Timestamp;
import java.util.List;

public class ExportUtil {

    public static boolean exportHeader(File file, String position, Long startTime, Long endTime) throws IOException{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, true)));
        try {
            out.write("\r\n\r\n\r\n");
            out.write("--------------------------------" + "\r\n");
            out.write(position + "\r\n");
            out.write("start time：" + new Timestamp(startTime).toString().substring(0,19)
                    + "    end time：" + new Timestamp(endTime).toString().substring(0,19) + "\r\n");
            out.write("-----time---------------value---" + "\r\n");
            out.write("\r\n\r\n\r\n");
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //向文件中输出历史数据
    public static boolean exportPv(File file, long startTime, long endTime, List<PersonnelCount> list) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, true)));
        try {
            for (int i = 0; i < list.size(); i++) {
                String val = list.get(i).getTimeString() + "       ";
                val = val + list.get(i).getCount();
                out.write(val + "\r\n");
            }
            out.flush();
            out.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
