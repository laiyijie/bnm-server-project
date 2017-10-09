package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.OptionMachineModelMappingCriteria;
import cn.bangnongmang.data.domain.OptionMachineModelMappingKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OptionMachineModelMappingMapper {
    long countByExample(OptionMachineModelMappingCriteria example);

    int deleteByExample(OptionMachineModelMappingCriteria example);

    int deleteByPrimaryKey(OptionMachineModelMappingKey key);

    int insert(OptionMachineModelMappingKey record);

    int insertSelective(OptionMachineModelMappingKey record);

    List<OptionMachineModelMappingKey> selectByExample(OptionMachineModelMappingCriteria example);

    int updateByExampleSelective(@Param("record") OptionMachineModelMappingKey record, @Param("example") OptionMachineModelMappingCriteria example);

    int updateByExample(@Param("record") OptionMachineModelMappingKey record, @Param("example") OptionMachineModelMappingCriteria example);
}