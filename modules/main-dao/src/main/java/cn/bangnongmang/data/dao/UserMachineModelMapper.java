package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.UserMachineModel;
import cn.bangnongmang.data.domain.UserMachineModelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMachineModelMapper {
    long countByExample(UserMachineModelCriteria example);

    int deleteByExample(UserMachineModelCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMachineModel record);

    int insertSelective(UserMachineModel record);

    List<UserMachineModel> selectByExample(UserMachineModelCriteria example);

    UserMachineModel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMachineModel record, @Param("example") UserMachineModelCriteria example);

    int updateByExample(@Param("record") UserMachineModel record, @Param("example") UserMachineModelCriteria example);

    int updateByPrimaryKeySelective(UserMachineModel record);

    int updateByPrimaryKey(UserMachineModel record);
}