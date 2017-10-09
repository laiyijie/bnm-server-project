package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.Region;
import cn.bangnongmang.data.domain.RegionCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegionMapper {
    long countByExample(RegionCriteria example);

    int deleteByExample(RegionCriteria example);

    int deleteByPrimaryKey(String idregion);

    int insert(Region record);

    int insertSelective(Region record);

    List<Region> selectByExample(RegionCriteria example);

    Region selectByPrimaryKey(String idregion);

    int updateByExampleSelective(@Param("record") Region record, @Param("example") RegionCriteria example);

    int updateByExample(@Param("record") Region record, @Param("example") RegionCriteria example);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}