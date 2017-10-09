package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.UserMachine;
import cn.bangnongmang.data.domain.UserMachineCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMachineMapper {
    long countByExample(UserMachineCriteria example);

    int deleteByExample(UserMachineCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMachine record);

    int insertSelective(UserMachine record);

    List<UserMachine> selectByExample(UserMachineCriteria example);

    UserMachine selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMachine record, @Param("example") UserMachineCriteria example);

    int updateByExample(@Param("record") UserMachine record, @Param("example") UserMachineCriteria example);

    int updateByPrimaryKeySelective(UserMachine record);

    int updateByPrimaryKey(UserMachine record);
}