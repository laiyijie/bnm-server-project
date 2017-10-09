package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.UserWorkCalendar;
import cn.bangnongmang.data.domain.UserWorkCalendarCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWorkCalendarMapper {
    long countByExample(UserWorkCalendarCriteria example);

    int deleteByExample(UserWorkCalendarCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserWorkCalendar record);

    int insertSelective(UserWorkCalendar record);

    List<UserWorkCalendar> selectByExample(UserWorkCalendarCriteria example);

    UserWorkCalendar selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserWorkCalendar record, @Param("example") UserWorkCalendarCriteria example);

    int updateByExample(@Param("record") UserWorkCalendar record, @Param("example") UserWorkCalendarCriteria example);

    int updateByPrimaryKeySelective(UserWorkCalendar record);

    int updateByPrimaryKey(UserWorkCalendar record);
}