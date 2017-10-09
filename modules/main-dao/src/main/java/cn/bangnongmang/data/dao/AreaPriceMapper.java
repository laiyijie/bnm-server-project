package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.AreaPrice;
import cn.bangnongmang.data.domain.AreaPriceCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaPriceMapper {
    long countByExample(AreaPriceCriteria example);

    int deleteByExample(AreaPriceCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(AreaPrice record);

    int insertSelective(AreaPrice record);

    List<AreaPrice> selectByExample(AreaPriceCriteria example);

    AreaPrice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AreaPrice record, @Param("example") AreaPriceCriteria example);

    int updateByExample(@Param("record") AreaPrice record, @Param("example") AreaPriceCriteria example);

    int updateByPrimaryKeySelective(AreaPrice record);

    int updateByPrimaryKey(AreaPrice record);
}