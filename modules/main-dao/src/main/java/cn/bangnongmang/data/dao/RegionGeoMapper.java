package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.RegionGeo;
import cn.bangnongmang.data.domain.RegionGeoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegionGeoMapper {
    long countByExample(RegionGeoCriteria example);

    int deleteByExample(RegionGeoCriteria example);

    int deleteByPrimaryKey(String region_id);

    int insert(RegionGeo record);

    int insertSelective(RegionGeo record);

    List<RegionGeo> selectByExample(RegionGeoCriteria example);

    RegionGeo selectByPrimaryKey(String region_id);

    int updateByExampleSelective(@Param("record") RegionGeo record, @Param("example") RegionGeoCriteria example);

    int updateByExample(@Param("record") RegionGeo record, @Param("example") RegionGeoCriteria example);

    int updateByPrimaryKeySelective(RegionGeo record);

    int updateByPrimaryKey(RegionGeo record);
}