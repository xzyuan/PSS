package com.example.demo.dao;

import com.example.demo.model.StaffInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface StaffDao {
    /**
     * 查询user表中所有用户
     * @return
     */
    @Select("select card_id,name,division_name from staff s left join division d on s.division_id = d.division_id order by card_id")
    List<StaffInfo> findAllStaffInfo();

    /**
     * 往user表中插入一个新的用户
     * @param card_id
     * @param name
     * @param division_id
     * @return
     */
    @Insert("insert into staff values(null,#{card_id},#{name},#{division_id})")
    int insertStaff(@Param("card_id") int card_id, @Param("name") String name, @Param("division_id") int division_id);

    /**
     * 根据卡号删除分组
     * @param card_id
     * @return
     */
    @Delete("delete from staff where card_id = #{card_id}")
    int deleteByCardId(int card_id);

    /**
     *  修改user中某个用户的分组号
     * @param name
     * @param division_id
     */
    @Update("update staff set division_id = #{division_id} where name = #{name}")
    void updateStaffGroupByCardId(@Param("name") String name, @Param("division_id") int division_id);
}
