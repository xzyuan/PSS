package com.example.demo.dao;


import com.example.demo.model.Division;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DivisionDao {

    @Insert("insert into division values(#{division_id}, #{division_name})")
    int insertGroup(@Param("division_id") int division_id, @Param("division_name") String division_name);

    @Select("select * from division order by division_id")
    List<Division> selectAllDivision();
}
