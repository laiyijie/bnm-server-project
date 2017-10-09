package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.PocketLog;
import cn.bangnongmang.data.domain.PocketLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PocketLogMapper {
    long countByExample(PocketLogCriteria example);

    int deleteByExample(PocketLogCriteria example);

    int deleteByPrimaryKey(String pocket_log_id);

    int insert(PocketLog record);

    int insertSelective(PocketLog record);

    List<PocketLog> selectByExample(PocketLogCriteria example);

    PocketLog selectByPrimaryKey(String pocket_log_id);

    int updateByExampleSelective(@Param("record") PocketLog record, @Param("example") PocketLogCriteria example);

    int updateByExample(@Param("record") PocketLog record, @Param("example") PocketLogCriteria example);

    int updateByPrimaryKeySelective(PocketLog record);

    int updateByPrimaryKey(PocketLog record);
}