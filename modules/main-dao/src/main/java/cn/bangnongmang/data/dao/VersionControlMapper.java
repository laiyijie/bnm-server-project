package cn.bangnongmang.data.dao;

import cn.bangnongmang.data.domain.VersionControl;
import cn.bangnongmang.data.domain.VersionControlCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VersionControlMapper {
    long countByExample(VersionControlCriteria example);

    int deleteByExample(VersionControlCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(VersionControl record);

    int insertSelective(VersionControl record);

    List<VersionControl> selectByExample(VersionControlCriteria example);

    VersionControl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VersionControl record, @Param("example") VersionControlCriteria example);

    int updateByExample(@Param("record") VersionControl record, @Param("example") VersionControlCriteria example);

    int updateByPrimaryKeySelective(VersionControl record);

    int updateByPrimaryKey(VersionControl record);
}