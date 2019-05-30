package com.example.demo.dao;

import com.example.demo.model.Event;
import com.example.demo.model.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EventDao {
    @Insert("insert into event values(null,#{card_id},#{reader_name},#{swipe_time},#{swipe_result})")
    int insertEvent(@Param("card_id") int card_id,
                    @Param("reader_name") String reader_name,
                    @Param("swipe_time") long swipe_time,
                    @Param("swipe_result") int swipe_result);

    @Select("<script>" +
            "select swipe_time,reader_name,swipe_result,name,e.card_id,t.division_name from " +
            "(select * from event where swipe_time between #{start_time} and #{end_time} " +
            "<if test='card_id != -1'> and card_id = #{card_id} </if> " +
            "<if test='swipe_result != -1'> and swipe_result = #{swipe_result} </if> " +
            "<if test='reader_name1 != null'> and reader_name in (#{reader_name1},#{reader_name2})  </if> " +
            "<if test='division_id != -1'> and card_id/1000 = #{division_id} </if>) e " +
            "left join (select * from staff s left join " +
            "(select * from division <if test='division_id != -1'> where division_id = #{division_id} </if>) d " +
            "on s.division_id = d.division_id ) t on e.card_id = t.card_id order by swipe_time; </script>")
    List<Record> selectRecord(@Param("card_id") int card_id,
                              @Param("division_id")int division_id,
                              @Param("reader_name1")String reader_name1,
                              @Param("reader_name2")String reader_name2,
                              @Param("swipe_result") int swipe_result,
                              @Param("start_time")long start_time,
                              @Param("end_time")long end_time);

    @Select("select swipe_time,reader_name,swipe_result,name,e.card_id,t.division_name from (select * from event where swipe_time between #{start_time} and #{end_time}) e left join (select * from staff s left join division d on s.division_id = d.division_id) t on e.card_id = t.card_id order by swipe_time;")
    List<Record> selectEventBySwipeTime(@Param("start_time")long start_time, @Param("end_time")long end_time);

    @Select("select swipe_time,reader_name,swipe_result,name,e.card_id,t.division_name from (select * from event where swipe_time between #{start_time} and #{end_time} and card_id = #{card_id}) e left join (select * from staff s left join division d on s.division_id = d.division_id) t on e.card_id = t.card_id order by swipe_time;")
    List<Record> selectEventByCardIdAndSwipeTime(@Param("card_id")int card_id, @Param("start_time")long start_time, @Param("end_time")long end_time);

    @Select("select swipe_time,reader_name,swipe_result,name,e.card_id,t.division_name from (select * from event where swipe_time between #{start_time} and #{end_time}) e left join (select * from staff s inner join (select * from division where division_id = #{division_id}) d on s.division_id = d.division_id) t on e.card_id = t.card_id order by swipe_time;")
    List<Record> selectEventByDivisionIdAndSwipeTime(@Param("division_id")int division_id, @Param("start_time")long start_time, @Param("end_time")long end_time);

    @Select("select swipe_time,reader_name,swipe_result,name,e.card_id,t.division_name from (select * from event where swipe_time between #{start_time} and #{end_time} and reader_name = #{reader_name}) e left join (select * from staff s inner join division d on s.division_id = d.division_id) t on e.card_id = t.card_id order by swipe_time;")
    List<Record> selectEventByReaderNameAndSwipeTime(@Param("reader_name")String reader_name, @Param("start_time")long start_time, @Param("end_time")long end_time);
    /**
     * 查询规定时间段内读卡器的记录
     * @param reader_name
     * @param start_time
     * @param end_time
     * @return
     */
    @Select("select * from event where reader_name = #{reader_name} and swipe_time between #{start_time} and #{end_time}")
    List<Event> selectEventBySwipeTimeAndReaderName(@Param("reader_name") String reader_name,@Param("start_time")long start_time, @Param("end_time")long end_time);

    /**
     * 查询规定时间段内读卡器的成功记录
     * @param reader_name
     * @param start_time
     * @param end_time
     * @return
     */
    @Select("select * from event where reader_name = #{reader_name} and swipe_time between #{start_time} and #{end_time} and swipe_result = 1")
    List<Event> selectSuccessEventBySwipeTimeAndReaderName(@Param("reader_name") String reader_name,@Param("start_time")long start_time, @Param("end_time")long end_time);

    /**
     * 查询规定时间段内读卡器的失败记录
     * @param reader_name
     * @param start_time
     * @param end_time
     * @return
     */
    @Select("select * from event where reader_name = #{reader_name} and swipe_time between #{start_time} and #{end_time} and swipe_result = 1")
    List<Event> selectFailedEventBySwipeTimeAndReaderName(@Param("reader_name") String reader_name,@Param("start_time")long start_time, @Param("end_time")long end_time);
}
