package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.GrabSeason;
import cn.bangnongmang.data.domain.GrabSeasonCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GrabSeasonMapper {
    long countByExample(GrabSeasonCriteria example);

    int deleteByExample(GrabSeasonCriteria example);

    int deleteByPrimaryKey(String id);

    int insert(GrabSeason record);

    int insertSelective(GrabSeason record);

    List<GrabSeason> selectByExample(GrabSeasonCriteria example);

    GrabSeason selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") GrabSeason record, @Param("example") GrabSeasonCriteria example);

    int updateByExample(@Param("record") GrabSeason record, @Param("example") GrabSeasonCriteria example);

    int updateByPrimaryKeySelective(GrabSeason record);

    int updateByPrimaryKey(GrabSeason record);
}