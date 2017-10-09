package cn.bangnongmang.admin.data.dao;

import cn.bangnongmang.admin.data.domain.RescRole;
import cn.bangnongmang.admin.data.domain.RescRoleCriteria;
import cn.bangnongmang.admin.data.domain.RescRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RescRoleMapper {
    long countByExample(RescRoleCriteria example);

    int deleteByExample(RescRoleCriteria example);

    int deleteByPrimaryKey(RescRoleKey key);

    int insert(RescRole record);

    int insertSelective(RescRole record);

    List<RescRole> selectByExample(RescRoleCriteria example);

    RescRole selectByPrimaryKey(RescRoleKey key);

    int updateByExampleSelective(@Param("record") RescRole record, @Param("example") RescRoleCriteria example);

    int updateByExample(@Param("record") RescRole record, @Param("example") RescRoleCriteria example);

    int updateByPrimaryKeySelective(RescRole record);

    int updateByPrimaryKey(RescRole record);
}