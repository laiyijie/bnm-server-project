package cn.bangnongmang.admin.data.dao;

import cn.bangnongmang.admin.data.domain.UserRoleCriteria;
import cn.bangnongmang.admin.data.domain.UserRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    long countByExample(UserRoleCriteria example);

    int deleteByExample(UserRoleCriteria example);

    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    List<UserRoleKey> selectByExample(UserRoleCriteria example);

    int updateByExampleSelective(@Param("record") UserRoleKey record, @Param("example") UserRoleCriteria example);

    int updateByExample(@Param("record") UserRoleKey record, @Param("example") UserRoleCriteria example);
}