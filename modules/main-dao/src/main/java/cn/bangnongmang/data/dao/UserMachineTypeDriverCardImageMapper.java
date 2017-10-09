package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.UserMachineTypeDriverCardImage;
import cn.bangnongmang.data.domain.UserMachineTypeDriverCardImageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMachineTypeDriverCardImageMapper {
    long countByExample(UserMachineTypeDriverCardImageCriteria example);

    int deleteByExample(UserMachineTypeDriverCardImageCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMachineTypeDriverCardImage record);

    int insertSelective(UserMachineTypeDriverCardImage record);

    List<UserMachineTypeDriverCardImage> selectByExample(UserMachineTypeDriverCardImageCriteria example);

    UserMachineTypeDriverCardImage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMachineTypeDriverCardImage record, @Param("example") UserMachineTypeDriverCardImageCriteria example);

    int updateByExample(@Param("record") UserMachineTypeDriverCardImage record, @Param("example") UserMachineTypeDriverCardImageCriteria example);

    int updateByPrimaryKeySelective(UserMachineTypeDriverCardImage record);

    int updateByPrimaryKey(UserMachineTypeDriverCardImage record);
}