package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OptionWorkingTypeMachineModelMappingCriteria;
import cn.bangnongmang.data.domain.OptionWorkingTypeMachineModelMappingKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptionWorkingTypeMachineModelMappingMapper {
    long countByExample(OptionWorkingTypeMachineModelMappingCriteria example);

    int deleteByExample(OptionWorkingTypeMachineModelMappingCriteria example);

    int deleteByPrimaryKey(OptionWorkingTypeMachineModelMappingKey key);

    int insert(OptionWorkingTypeMachineModelMappingKey record);

    int insertSelective(OptionWorkingTypeMachineModelMappingKey record);

    List<OptionWorkingTypeMachineModelMappingKey> selectByExample(OptionWorkingTypeMachineModelMappingCriteria example);

    int updateByExampleSelective(@Param("record") OptionWorkingTypeMachineModelMappingKey record, @Param("example") OptionWorkingTypeMachineModelMappingCriteria example);

    int updateByExample(@Param("record") OptionWorkingTypeMachineModelMappingKey record, @Param("example") OptionWorkingTypeMachineModelMappingCriteria example);
}