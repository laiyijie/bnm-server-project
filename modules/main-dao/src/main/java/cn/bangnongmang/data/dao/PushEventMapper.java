package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.PushEvent;
import cn.bangnongmang.data.domain.PushEventCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushEventMapper {
    long countByExample(PushEventCriteria example);

    int deleteByExample(PushEventCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(PushEvent record);

    int insertSelective(PushEvent record);

    List<PushEvent> selectByExample(PushEventCriteria example);

    PushEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") PushEvent record, @Param("example") PushEventCriteria example);

    int updateByExample(@Param("record") PushEvent record, @Param("example") PushEventCriteria example);

    int updateByPrimaryKeySelective(PushEvent record);

    int updateByPrimaryKey(PushEvent record);
}