package com.example.demo.dao;

import com.example.demo.model.PersonnelCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PersonnelCountDao {
    @Select("select * from ${table_name} where time between #{start_time} and #{end_time} order by time")
    List<PersonnelCount> selectPCByPosition(@Param("table_name") String position, @Param("start_time") long start_time, @Param("end_time") long end_time);

    @Select("select * from ${table_name} order by time desc limit 1 ")
    List<PersonnelCount> selectNewestPCByPosition(@Param("table_name") String position);
}
