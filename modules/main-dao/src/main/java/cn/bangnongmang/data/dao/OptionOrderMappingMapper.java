package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OptionOrderMappingCriteria;
import cn.bangnongmang.data.domain.OptionOrderMappingKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptionOrderMappingMapper {
    long countByExample(OptionOrderMappingCriteria example);

    int deleteByExample(OptionOrderMappingCriteria example);

    int deleteByPrimaryKey(OptionOrderMappingKey key);

    int insert(OptionOrderMappingKey record);

    int insertSelective(OptionOrderMappingKey record);

    List<OptionOrderMappingKey> selectByExample(OptionOrderMappingCriteria example);

    int updateByExampleSelective(@Param("record") OptionOrderMappingKey record, @Param("example") OptionOrderMappingCriteria example);

    int updateByExample(@Param("record") OptionOrderMappingKey record, @Param("example") OptionOrderMappingCriteria example);
}