package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingCriteria;
import cn.bangnongmang.data.domain.OptionClusterWorkingTypeMappingKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptionClusterWorkingTypeMappingMapper {
    long countByExample(OptionClusterWorkingTypeMappingCriteria example);

    int deleteByExample(OptionClusterWorkingTypeMappingCriteria example);

    int deleteByPrimaryKey(OptionClusterWorkingTypeMappingKey key);

    int insert(OptionClusterWorkingTypeMappingKey record);

    int insertSelective(OptionClusterWorkingTypeMappingKey record);

    List<OptionClusterWorkingTypeMappingKey> selectByExample(OptionClusterWorkingTypeMappingCriteria example);

    int updateByExampleSelective(@Param("record") OptionClusterWorkingTypeMappingKey record, @Param("example") OptionClusterWorkingTypeMappingCriteria example);

    int updateByExample(@Param("record") OptionClusterWorkingTypeMappingKey record, @Param("example") OptionClusterWorkingTypeMappingCriteria example);
}