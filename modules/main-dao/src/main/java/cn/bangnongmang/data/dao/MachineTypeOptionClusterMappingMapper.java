package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.MachineTypeOptionClusterMappingCriteria;
import cn.bangnongmang.data.domain.MachineTypeOptionClusterMappingKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MachineTypeOptionClusterMappingMapper {
    long countByExample(MachineTypeOptionClusterMappingCriteria example);

    int deleteByExample(MachineTypeOptionClusterMappingCriteria example);

    int deleteByPrimaryKey(MachineTypeOptionClusterMappingKey key);

    int insert(MachineTypeOptionClusterMappingKey record);

    int insertSelective(MachineTypeOptionClusterMappingKey record);

    List<MachineTypeOptionClusterMappingKey> selectByExample(MachineTypeOptionClusterMappingCriteria example);

    int updateByExampleSelective(@Param("record") MachineTypeOptionClusterMappingKey record, @Param("example") MachineTypeOptionClusterMappingCriteria example);

    int updateByExample(@Param("record") MachineTypeOptionClusterMappingKey record, @Param("example") MachineTypeOptionClusterMappingCriteria example);
}