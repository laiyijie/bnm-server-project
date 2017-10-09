package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.VisitLogItem;
import cn.bangnongmang.data.domain.VisitLogItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VisitLogItemMapper {
    long countByExample(VisitLogItemCriteria example);

    int deleteByExample(VisitLogItemCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(VisitLogItem record);

    int insertSelective(VisitLogItem record);

    List<VisitLogItem> selectByExample(VisitLogItemCriteria example);

    VisitLogItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VisitLogItem record, @Param("example") VisitLogItemCriteria example);

    int updateByExample(@Param("record") VisitLogItem record, @Param("example") VisitLogItemCriteria example);

    int updateByPrimaryKeySelective(VisitLogItem record);

    int updateByPrimaryKey(VisitLogItem record);
}