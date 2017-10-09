package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.UserMachineOptionDetailMappingCriteria;
import cn.bangnongmang.data.domain.UserMachineOptionDetailMappingKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMachineOptionDetailMappingMapper {
    long countByExample(UserMachineOptionDetailMappingCriteria example);

    int deleteByExample(UserMachineOptionDetailMappingCriteria example);

    int deleteByPrimaryKey(UserMachineOptionDetailMappingKey key);

    int insert(UserMachineOptionDetailMappingKey record);

    int insertSelective(UserMachineOptionDetailMappingKey record);

    List<UserMachineOptionDetailMappingKey> selectByExample(UserMachineOptionDetailMappingCriteria example);

    int updateByExampleSelective(@Param("record") UserMachineOptionDetailMappingKey record, @Param("example") UserMachineOptionDetailMappingCriteria example);

    int updateByExample(@Param("record") UserMachineOptionDetailMappingKey record, @Param("example") UserMachineOptionDetailMappingCriteria example);
}