package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.UserMachineAuthImage;
import cn.bangnongmang.data.domain.UserMachineAuthImageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMachineAuthImageMapper {
    long countByExample(UserMachineAuthImageCriteria example);

    int deleteByExample(UserMachineAuthImageCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMachineAuthImage record);

    int insertSelective(UserMachineAuthImage record);

    List<UserMachineAuthImage> selectByExample(UserMachineAuthImageCriteria example);

    UserMachineAuthImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMachineAuthImage record, @Param("example") UserMachineAuthImageCriteria example);

    int updateByExample(@Param("record") UserMachineAuthImage record, @Param("example") UserMachineAuthImageCriteria example);

    int updateByPrimaryKeySelective(UserMachineAuthImage record);

    int updateByPrimaryKey(UserMachineAuthImage record);
}