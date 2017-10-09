package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.Pocket;
import cn.bangnongmang.data.domain.PocketCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PocketMapper {
    long countByExample(PocketCriteria example);

    int deleteByExample(PocketCriteria example);

    int deleteByPrimaryKey(Long uid);

    int insert(Pocket record);

    int insertSelective(Pocket record);

    List<Pocket> selectByExample(PocketCriteria example);

    Pocket selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") Pocket record, @Param("example") PocketCriteria example);

    int updateByExample(@Param("record") Pocket record, @Param("example") PocketCriteria example);

    int updateByPrimaryKeySelective(Pocket record);

    int updateByPrimaryKey(Pocket record);
}