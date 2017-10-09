package cn.bangnongmang.admin.data.dao;

import cn.bangnongmang.admin.data.domain.AreaDict;
import cn.bangnongmang.admin.data.domain.AreaDictCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaDictMapper {
    long countByExample(AreaDictCriteria example);

    int deleteByExample(AreaDictCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(AreaDict record);

    int insertSelective(AreaDict record);

    List<AreaDict> selectByExample(AreaDictCriteria example);

    AreaDict selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AreaDict record, @Param("example") AreaDictCriteria example);

    int updateByExample(@Param("record") AreaDict record, @Param("example") AreaDictCriteria example);

    int updateByPrimaryKeySelective(AreaDict record);

    int updateByPrimaryKey(AreaDict record);
}