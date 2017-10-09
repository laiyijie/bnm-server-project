package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.UserMachineType;
import cn.bangnongmang.data.domain.UserMachineTypeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMachineTypeMapper {
    long countByExample(UserMachineTypeCriteria example);

    int deleteByExample(UserMachineTypeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMachineType record);

    int insertSelective(UserMachineType record);

    List<UserMachineType> selectByExample(UserMachineTypeCriteria example);

    UserMachineType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMachineType record, @Param("example") UserMachineTypeCriteria example);

    int updateByExample(@Param("record") UserMachineType record, @Param("example") UserMachineTypeCriteria example);

    int updateByPrimaryKeySelective(UserMachineType record);

    int updateByPrimaryKey(UserMachineType record);
}